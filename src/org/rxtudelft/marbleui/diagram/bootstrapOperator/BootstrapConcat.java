package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/19/14.
 */
public class BootstrapConcat extends BootstrapOperator2<SimpleMarbleModel, SimpleMarbleModel> {

    public BootstrapConcat() {
        super("Concat");
    }

    @Override
    public Observable call2(TestScheduler s, Observable in1, Observable in2) {
        return Observable.concat(in1, in2);
    }
}
