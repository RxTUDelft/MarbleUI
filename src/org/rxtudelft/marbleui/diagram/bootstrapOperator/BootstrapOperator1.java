package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;

import java.util.List;

/**
 * Created by ferdy on 5/22/14.
 */
public abstract class BootstrapOperator1 extends BootstrapOperator {

    public Observable<SimpleMarbleModel> call(List<Observable<SimpleMarbleModel>> observables) {
        return this.call1(observables.get(0));
    }

    public abstract Observable<SimpleMarbleModel> call1(Observable<SimpleMarbleModel> in1);
}
