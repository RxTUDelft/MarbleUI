package org.rxtudelft.marbleui.diagram;

import org.rxtudelft.marbleui.diagram.bootstrapOperator.BootstrapOperator;
import rx.Observable;
import rx.schedulers.TestScheduler;
import rx.schedulers.Timestamped;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by ferdy on 5/8/14.
 */
public class MarbleDiagramModel<T extends MarbleModel> {

    private List<InObservableModel> inputs;
    private BootstrapOperator<T> operator;
    private ObservableModel<T> output;

    public MarbleDiagramModel(List<InObservableModel> observables, BootstrapOperator operator) {
        this.operator = operator;
        this.inputs = observables;
        this.output = operator.getOutObservableModelFactory().getOutObservable();
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
        List<Observable<SimpleMarbleModel>> inputs = this.inputs.stream()
                .map(o -> o.testSubject(ts)).collect(Collectors.<Observable<SimpleMarbleModel>>toList());;
        
        //calculate timestamped output
        Observable<Timestamped<T>> outputObs = operator.call(inputs).map(marble -> {
            return new Timestamped<T>(ts.now(), marble);
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

    public List<InObservableModel> getInputs() {
        return inputs;
    }

    public BootstrapOperator getOperator() {
        return operator;
    }

    public ObservableModel getOutput() {
        return output;
    }
}
