package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ferdy on 5/16/14.
 */
public class BootstrapMap implements BootstrapOperator1<SimpleMarbleModel> {

    private Func1<SimpleMarbleModel, SimpleMarbleModel> mapping;

public BootstrapMap(Func1<SimpleMarbleModel, SimpleMarbleModel> mapping) {
        this.mapping = mapping;
    }

    @Override
    public Observable<SimpleMarbleModel> call1(Observable<SimpleMarbleModel> toMap) {
        return toMap.map(mapping);
    }
}
