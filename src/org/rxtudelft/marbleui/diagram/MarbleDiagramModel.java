package org.rxtudelft.marbleui.diagram;

import org.rxtudelft.marbleui.diagram.initOperator.InitOperator;
import rx.Observable;
import rx.schedulers.TestScheduler;
import rx.schedulers.Timestamped;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by ferdy on 5/8/14.
 */
public class MarbleDiagramModel {
    private List<ObservableModel> observables;
    private InitOperator operator;
    private ObservableModel output;

    public MarbleDiagramModel(List<ObservableModel> observables, InitOperator operator) {
        this.operator = operator;
        this.observables = observables;
        this.output = new ObservableModel(new HashMap<>());
    }

    public void calcOutput() {
        TestScheduler ts = new TestScheduler();

        List<Observable<MarbleModel>> inputs = observables.stream()
                .map(o -> o.testSubject(ts)).collect(Collectors.<Observable<MarbleModel>>toList());

        Observable<Timestamped<MarbleModel>> outputObs = operator.call(inputs).map(o -> new Timestamped<>(ts.now(), o));
        ArrayList<Timestamped<MarbleModel>> list = new ArrayList<>();

        ts.advanceTimeTo(ObservableModel.MAX_TIME, TimeUnit.MILLISECONDS);

        outputObs.subscribe(o -> list.add(o));

        output = new ObservableModel(list.stream().collect(Collectors.toMap(Timestamped::getTimestampMillis, Timestamped::getValue)));
    }
}
