package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by ferdy on 6/25/14.
 */
public class BootstrapMergeDelayError extends BootstrapOperator2 {

    public BootstrapMergeDelayError() {
        super("MergeDelayError");
    }

    @Override
    public Observable call2(Scheduler s, Observable in1, Observable in2) {
        return Observable.mergeDelayError(in1, in2);
    }
}
