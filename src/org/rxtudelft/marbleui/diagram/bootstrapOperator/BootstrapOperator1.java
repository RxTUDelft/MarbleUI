package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.Scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ferdy on 5/22/14.
 */
public abstract class BootstrapOperator1<I extends MarbleModel, O extends MarbleModel> extends
        BootstrapOperator<I, O> {

    protected BootstrapOperator1(String label) {
        super(label);
    }

    public Observable<O> call(Scheduler s, List<Observable<I>> observables) {
        return this.call1(s, observables.get(0));
    }

    public abstract Observable<O> call1(Scheduler s, Observable<I> in1);

    @Override
    public List<ObservableModel> getInObservableModels() {
        List<ObservableModel> inObsModels = new ArrayList<>();
        inObsModels.add(new ObservableModel());

        return inObsModels;
    }
}
