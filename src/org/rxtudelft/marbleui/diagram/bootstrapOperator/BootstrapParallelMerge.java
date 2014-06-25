package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.*;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.TestScheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ferdy on 6/23/14.
 */
public class BootstrapParallelMerge extends BootstrapOperator1<ChildObservableModel, ChildObservableModel> {
    private int distributeOver;

    public BootstrapParallelMerge(int distributeOver) {
        super("ParallelMerge");
        this.distributeOver = distributeOver;
    }

    @Override
    public Observable<ChildObservableModel> call1(Scheduler s, Observable<ChildObservableModel> in1) {
        ChildObservableModel merged = new ChildObservableModel();

        Observable.parallelMerge(in1.map(inGroup -> {
            //Create an obs for all in streams
            return inGroup.testSubject((TestScheduler)s);
        }), distributeOver);

        return Observable.from();
    }

    @Override
    public List<ObservableModel> getInObservableModels() {
        List<ObservableModel> inObsModels = new ArrayList<>();
        inObsModels.add(new ComplexObservableModel());

        return inObsModels;
    }
}
