package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModelFactory;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.functions.Func1;

import java.util.List;

/**
 * Created by ferdy on 5/16/14.
 */
public abstract class BootstrapOperator<T extends MarbleModel> implements Func1<List<Observable<SimpleMarbleModel>>, Observable<T>> {

    public ObservableModelFactory<T> getOutObservableModelFactory() {
        return new ObservableModelFactory<T>();
    }
}
