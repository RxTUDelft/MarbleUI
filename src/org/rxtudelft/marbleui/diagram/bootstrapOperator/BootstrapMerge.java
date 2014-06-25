package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.NGonMarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by ferdy on 6/25/14.
 */
public class BootstrapMerge extends BootstrapOperator2<NGonMarbleModel, NGonMarbleModel> {
    public BootstrapMerge() {
        super("Merge");
    }

    @Override
    public Observable<NGonMarbleModel> call2(Scheduler s, Observable<NGonMarbleModel> in1, Observable<NGonMarbleModel> in2) {
        return Observable.merge(in1, in2);
    }
}
