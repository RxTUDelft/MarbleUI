package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 5/16/14.
 */
public class BootstrapMap extends BootstrapOperator1<SimpleMarbleModel, SimpleMarbleModel> {

    private Func1<SimpleMarbleModel, SimpleMarbleModel> mapping;

    public BootstrapMap(Func1<SimpleMarbleModel, SimpleMarbleModel> mapping) {
        super("Map");
        this.mapping = mapping;
    }

    @Override
    public Observable<SimpleMarbleModel> call1(TestScheduler s, Observable<SimpleMarbleModel> toMap) {
        return toMap.map(mapping);
    }

}
