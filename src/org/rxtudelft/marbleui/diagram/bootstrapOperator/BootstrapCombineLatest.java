package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.functions.Func2;

/**
 * Created by ferdy on 6/18/14.
 */
public class BootstrapCombineLatest implements BootstrapOperator2 {

    private Func2<MarbleModel, MarbleModel, MarbleModel> zipFn;

    public BootstrapCombineLatest(Func2<MarbleModel, MarbleModel, MarbleModel> zipFn) {
        this.zipFn = zipFn;
    }

    @Override
    public Observable<MarbleModel> call2(Observable<MarbleModel> in1, Observable<MarbleModel> in2) {
        return Observable.combineLatest(in1, in2, this.zipFn);
    }
}
