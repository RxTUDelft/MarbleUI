package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.NGonMarbleModel;
import rx.Observable;
import rx.functions.Func2;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/18/14.
 */
public class BootstrapZip extends BootstrapOperator2<NGonMarbleModel, NGonMarbleModel> {

    private Func2<NGonMarbleModel, NGonMarbleModel, NGonMarbleModel> zipFn;

    public BootstrapZip(Func2<NGonMarbleModel, NGonMarbleModel, NGonMarbleModel> zipFn) {
        super("Zip");
        this.zipFn = zipFn;
    }

    @Override
    public Observable<NGonMarbleModel> call2(TestScheduler s, Observable<NGonMarbleModel> in1, Observable<NGonMarbleModel> in2) {
        return in1.zip(in2, this.zipFn);
    }
}
