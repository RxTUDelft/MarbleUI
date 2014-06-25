package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.ChildObservableModel;
import org.rxtudelft.marbleui.diagram.ComplexObservableModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import rx.Observable;
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
    public Observable<ChildObservableModel> call1(TestScheduler s, Observable<ChildObservableModel> in1) {
        Observable.parallelMerge(in1.map(modelToObservable((TestScheduler) s)), distributeOver);

        return Observable.from();
    }

    @Override
    public List<ObservableModel> getInObservableModels() {
        List<ObservableModel> inObsModels = new ArrayList<>();
        inObsModels.add(new ComplexObservableModel());

        return inObsModels;
    }
}
