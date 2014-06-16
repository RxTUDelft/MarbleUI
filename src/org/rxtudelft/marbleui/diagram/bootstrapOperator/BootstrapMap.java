package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ferdy on 5/16/14.
 */
public class BootstrapMap implements BootstrapOperator1 {

    private Func1<MarbleModel, MarbleModel> mapping;

    public BootstrapMap(Func1<MarbleModel, MarbleModel> mapping) {
        this.mapping = mapping;
    }

    @Override
    public Observable<MarbleModel> call1(Observable<MarbleModel> toMap) {
        return toMap.map(mapping);
    }
}
