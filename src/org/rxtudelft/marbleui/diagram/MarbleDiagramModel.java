package org.rxtudelft.marbleui.diagram;

import org.rxtudelft.marbleui.diagram.bootstrapOperator.BootstrapOperator;
import rx.Observable;
import rx.schedulers.TestScheduler;
import rx.schedulers.Timestamped;

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
        this.output = operator.getOutObservableModelFactory().getOutObservable();
        this.calcOutput();
        //subscribe to inputs, recalculate output onNext
        this.inputs.forEach(i -> i.getChangeObs().subscribe(s -> this.calcOutput()));
    }

    public void calcOutput() {
        TestScheduler ts = new TestScheduler();

        //create list of input rxObservables
        List<Observable<SimpleMarbleModel>> inputs = this.inputs.stream()
                .map(o -> o.testSubject(ts)).collect(Collectors.<Observable<SimpleMarbleModel>>toList());
        
        //calculate timestamped output
        Observable<Timestamped<MarbleModel>> outputObs = operator.call(inputs).map(marble ->
                new Timestamped<>(ts.now(), marble));

        // remove old marbles from output
        output.getMarbles().clear();

        //put all marbles on output
        outputObs.subscribe( o -> MarbleDiagramModel.this.output.put(o.getTimestampMillis(), o.getValue()),
                e -> MarbleDiagramModel.this.output.put(ts.now(), new SimpleErrorModel()),
                () -> MarbleDiagramModel.this.output.put(ts.now(), new SimpleCompletedModel())
        );

        //advance time past to max input time
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
