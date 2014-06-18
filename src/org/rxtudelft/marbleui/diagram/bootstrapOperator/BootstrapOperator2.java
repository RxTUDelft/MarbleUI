package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;

import java.util.List;

/**
 * Created by ferdy on 5/22/14.
 */
public abstract class BootstrapOperator2<T extends MarbleModel> extends BootstrapOperator<T> {
    public Observable<T> call(List<Observable<SimpleMarbleModel>> observables) {
        return this.call2(observables.get(0), observables.get(1));
    }

    public abstract Observable<T> call2(Observable<SimpleMarbleModel> in1, Observable<SimpleMarbleModel> in2);
}
