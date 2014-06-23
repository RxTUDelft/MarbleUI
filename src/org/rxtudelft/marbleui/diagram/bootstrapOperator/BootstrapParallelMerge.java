package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.*;
import rx.Observable;
import rx.Scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ferdy on 6/23/14.
 */
public class BootstrapParallelMerge extends BootstrapOperator1<ChildObservableModel, ChildObservableModel> {
    private int distributeOver;

    public BootstrapParallelMerge(int distributeOver) {
        this.distributeOver = distributeOver;
    }

    @Override
    public Observable<ChildObservableModel> call1(Scheduler s, Observable<ChildObservableModel> in1) {
        return Observable.from();
    }

    @Override
    public List<ObservableModel> getInObservableModels() {
        List<ObservableModel> inObsModels = new ArrayList<>();
        inObsModels.add(new ComplexObservableModel());

        return inObsModels;
    }
}
