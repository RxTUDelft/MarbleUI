package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModelFactory;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.functions.Func2;

/**
 * Created by ferdy on 6/18/14.
 */
public class BootstrapCombineLatest extends BootstrapOperator2<SimpleMarbleModel> {

    private Func2<SimpleMarbleModel, SimpleMarbleModel, SimpleMarbleModel> zipFn;

    public BootstrapCombineLatest(Func2<SimpleMarbleModel, SimpleMarbleModel, SimpleMarbleModel> zipFn) {
        this.zipFn = zipFn;
    }

    @Override
    public Observable<SimpleMarbleModel> call2(Observable<SimpleMarbleModel> in1, Observable<SimpleMarbleModel> in2) {
        return Observable.combineLatest(in1, in2, this.zipFn);
    }
}
