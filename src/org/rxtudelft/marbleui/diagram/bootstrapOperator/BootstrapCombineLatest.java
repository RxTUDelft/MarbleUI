package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.functions.Func2;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/18/14.
 */
public class BootstrapCombineLatest extends BootstrapOperator2<SimpleMarbleModel, SimpleMarbleModel> {

    private Func2<SimpleMarbleModel, SimpleMarbleModel, SimpleMarbleModel> zipFn;

    public BootstrapCombineLatest(Func2<SimpleMarbleModel, SimpleMarbleModel, SimpleMarbleModel> zipFn) {
        super("Combine latest");
        this.zipFn = zipFn;
    }

    @Override
    public Observable<SimpleMarbleModel> call2(TestScheduler s, Observable<SimpleMarbleModel> in1, Observable<SimpleMarbleModel> in2) {
        return Observable.combineLatest(in1, in2, this.zipFn);
    }
}
