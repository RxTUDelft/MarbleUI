package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.NGonMarbleModel;
import rx.Observable;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/25/14.
 */
public class BootstrapMerge extends BootstrapOperator2<NGonMarbleModel, NGonMarbleModel> {
    public BootstrapMerge() {
        super("Merge");
    }

    @Override
    public Observable<NGonMarbleModel> call2(TestScheduler s, Observable<NGonMarbleModel> in1, Observable<NGonMarbleModel> in2) {
        return Observable.merge(in1, in2);
    }
}
