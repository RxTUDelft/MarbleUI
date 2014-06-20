package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.ObservableModelFactory;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.functions.Func1;

import java.util.List;

/**
 * Created by ferdy on 5/16/14.
 */
public abstract class BootstrapOperator implements Func1<List<Observable<SimpleMarbleModel>>, Observable<SimpleMarbleModel>> {

    public ObservableModelFactory getOutObservableModelFactory() {
        return new ObservableModelFactory();
    }
}
