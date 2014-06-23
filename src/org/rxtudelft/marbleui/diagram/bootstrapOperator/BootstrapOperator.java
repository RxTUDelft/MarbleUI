package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Func2;

import java.util.List;

/**
 * Created by ferdy on 5/16/14.
 */
public abstract class BootstrapOperator<I extends MarbleModel, O extends MarbleModel> implements
        Func2<Scheduler, List<Observable<I>>, Observable<O>> {

    @Override
    public abstract Observable<O> call(Scheduler s, List<Observable<I>> is);

    public ObservableModel getOutObservableModel() {
        return new ObservableModel();
    }

    abstract public List<ObservableModel> getInObservableModels();
}
