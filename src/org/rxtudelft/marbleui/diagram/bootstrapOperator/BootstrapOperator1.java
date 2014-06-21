package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.Scheduler;

import java.util.List;

/**
 * Created by ferdy on 5/22/14.
 */
public abstract class BootstrapOperator1 extends BootstrapOperator {

    public Observable<? extends MarbleModel> call(Scheduler s, List<Observable<SimpleMarbleModel>> observables) {
        return this.call1(s, observables.get(0));
    }

    public abstract Observable<? extends MarbleModel> call1(Scheduler s, Observable<SimpleMarbleModel> in1);
}
