package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.Scheduler;

import java.util.List;

/**
 * Created by ferdy on 5/22/14.
 */
public abstract class BootstrapOperator2 extends BootstrapOperator {

    public Observable<SimpleMarbleModel> call(Scheduler s, List<Observable<SimpleMarbleModel>> observables) {
        return this.call2(s, observables.get(0), observables.get(1));
    }

    public abstract Observable<SimpleMarbleModel> call2(Scheduler s, Observable<SimpleMarbleModel> in1, Observable<SimpleMarbleModel> in2);
}
