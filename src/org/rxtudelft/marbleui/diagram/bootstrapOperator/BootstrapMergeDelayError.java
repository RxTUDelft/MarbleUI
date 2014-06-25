package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.NGonMarbleModel;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/25/14.
 */
public class BootstrapMergeDelayError extends BootstrapOperator2<NGonMarbleModel, NGonMarbleModel> {

    public BootstrapMergeDelayError() {
        super("MergeDelayError");
    }

    @Override
    public Observable call2(TestScheduler s, Observable<NGonMarbleModel> in1, Observable<NGonMarbleModel> in2) {
        return Observable.mergeDelayError(in1, in2);
    }
}
