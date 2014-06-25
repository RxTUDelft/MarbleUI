package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/18/14.
 */
public class BootstrapDistinctUntilChanged extends BootstrapOperator1<SimpleMarbleModel, SimpleMarbleModel> {

    public BootstrapDistinctUntilChanged() {
        super("DistinctUntilChanged");
    }

    @Override
    public Observable<SimpleMarbleModel> call1(TestScheduler s, Observable<SimpleMarbleModel> in1) {
        return in1.distinct();
    }
}
