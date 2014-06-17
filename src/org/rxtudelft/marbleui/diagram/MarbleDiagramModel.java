package org.rxtudelft.marbleui.diagram;

import org.rxtudelft.marbleui.diagram.bootstrapOperator.BootstrapOperator;
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
    private BootstrapOperator operator;
    private ObservableModel output;

    public MarbleDiagramModel(List<ObservableModel> observables, BootstrapOperator operator) {
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
                .map(o -> o.testSubject(ts)).collect(Collectors.<Observable<MarbleModel>>toList());;
        
        //calculate timestamped output
        Observable<Timestamped<MarbleModel>> outputObs = operator.call(inputs).map(marble -> {
            return new Timestamped<MarbleModel>(ts.now(), marble);
        });
        //outputObs.subscribe(System.out::println, System.err::println);
        ArrayList<Timestamped<MarbleModel>> list = new ArrayList<>();

        //advance time past to max input time

        //put all marbles on output
        outputObs.subscribe(o -> {
            MarbleDiagramModel.this.output.put(o.getTimestampMillis(), o.getValue());
        });
        ts.advanceTimeTo(ObservableModel.MAX_TIME, TimeUnit.MILLISECONDS);
    }

    public List<ObservableModel> getInputs() {
        return inputs;
    }

    public BootstrapOperator getOperator() {
        return operator;
    }

    public ObservableModel getOutput() {
        return output;
    }
}
