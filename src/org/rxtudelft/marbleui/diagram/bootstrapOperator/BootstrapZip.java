package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.functions.Func2;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/18/14.
 */
public class BootstrapZip extends BootstrapOperator2<SimpleMarbleModel, SimpleMarbleModel> {

    private Func2<SimpleMarbleModel, SimpleMarbleModel, SimpleMarbleModel> zipFn;

    public BootstrapZip(Func2<SimpleMarbleModel, SimpleMarbleModel, SimpleMarbleModel> zipFn) {
        super("Zip");
        this.zipFn = zipFn;
    }

    @Override
    public Observable<SimpleMarbleModel> call2(TestScheduler s, Observable<SimpleMarbleModel> in1, Observable<SimpleMarbleModel> in2) {
        return in1.zip(in2, this.zipFn);
    }
}
