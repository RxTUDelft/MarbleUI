package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by ferdy on 6/18/14.
 */
public class BootstrapDistinct extends BootstrapOperator1 {

    @Override
    public Observable<SimpleMarbleModel> call1(Scheduler s, Observable<SimpleMarbleModel> in1) {
        return in1.distinct();
    }
}
