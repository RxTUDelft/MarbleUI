package org.rxtudelft.marbleui.diagram;

import org.rxtudelft.marbleui.diagram.initOperator.InitOperator;
import rx.Observable;
import rx.schedulers.TestScheduler;
import rx.schedulers.Timestamped;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by ferdy on 5/8/14.
 */
public class MarbleDiagramModel {

    private List<ObservableModel> inputs;
    private InitOperator operator;
    private ObservableModel output;

    public MarbleDiagramModel(List<ObservableModel> observables, InitOperator operator) {
        this.operator = operator;
        this.inputs = observables;
        this.output = new ObservableModel();
        this.calcOutput();

        //subscribe to inputs, recalc output onNextd
        this.inputs.forEach(i -> {
            i.getChangeObs().subscribe(s -> {
                this.calcOutput();
            });
        });
    }

    public void calcOutput() {
        TestScheduler ts = new TestScheduler();

        //create list of input rxObservables
        List<Observable<MarbleModel>> inputs = this.inputs.stream()
                .map(o -> o.testSubject(ts)).collect(Collectors.<Observable<MarbleModel>>toList());

        //calculate timestamped output
        Observable<Timestamped<MarbleModel>> outputObs = operator.call(inputs).timestamp();
        ArrayList<Timestamped<MarbleModel>> list = new ArrayList<>();

        //advance time past to max input time
        ts.advanceTimeTo(ObservableModel.MAX_TIME, TimeUnit.MILLISECONDS);

        //put all marbles on output
        outputObs.subscribe(o -> {
            System.out.println(o);
            MarbleDiagramModel.this.output.put(o.getTimestampMillis(), o.getValue());
        });

    }

    public List<ObservableModel> getInputs() {
        return inputs;
    }

    public InitOperator getOperator() {
        return operator;
    }

    public ObservableModel getOutput() {
        return output;
    }
}
