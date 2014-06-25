package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import rx.Observable;
import rx.schedulers.TestScheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ferdy on 5/22/14.
 */
public abstract class BootstrapOperator2<I extends MarbleModel, O extends MarbleModel> extends
        BootstrapOperator<I, O> {

    public BootstrapOperator2(String label) {
        super(label);
    }

    public Observable<O> call(TestScheduler s, List<Observable<I>> observables) {
        return this.call2(s, observables.get(0), observables.get(1));
    }

    public abstract Observable<O> call2(TestScheduler s, Observable<I> in1, Observable<I> in2);

    @Override
    public List<ObservableModel> getInObservableModels() {
        List<ObservableModel> inObsModels = new ArrayList<>();
        inObsModels.add(new ObservableModel());
        inObsModels.add(new ObservableModel());

        return inObsModels;
    }
}
