package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.NGonMarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.functions.Func2;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/18/14.
 */
public class BootstrapCombineLatest extends BootstrapOperator2<NGonMarbleModel, NGonMarbleModel> {

    private Func2<NGonMarbleModel, NGonMarbleModel, NGonMarbleModel> zipFn;

    public BootstrapCombineLatest(Func2<NGonMarbleModel, NGonMarbleModel, NGonMarbleModel> zipFn) {
        super("Combine latest");
        this.zipFn = zipFn;
    }

    @Override
    public Observable<NGonMarbleModel> call2(TestScheduler s, Observable<NGonMarbleModel> in1, Observable<NGonMarbleModel> in2) {
        return Observable.combineLatest(in1, in2, this.zipFn);
    }
}
