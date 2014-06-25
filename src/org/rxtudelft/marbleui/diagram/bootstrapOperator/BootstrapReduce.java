package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.NGonMarbleModel;
import rx.Observable;
import rx.functions.Func2;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/25/14.
 */
public class BootstrapReduce extends BootstrapOperator1<NGonMarbleModel, NGonMarbleModel> {

    private Func2<NGonMarbleModel, NGonMarbleModel, NGonMarbleModel> reduceFunc;
    private NGonMarbleModel initialValue;
    public BootstrapReduce(NGonMarbleModel initialValue, Func2<NGonMarbleModel, NGonMarbleModel, NGonMarbleModel> reduceFunc) {
        super("Reduce");
        this.initialValue = initialValue;
        this.reduceFunc = reduceFunc;
    }

    @Override
    public Observable<NGonMarbleModel> call1(TestScheduler s, Observable<NGonMarbleModel> in1) {

        return in1.reduce(
                this.reduceFunc);
    }
}
