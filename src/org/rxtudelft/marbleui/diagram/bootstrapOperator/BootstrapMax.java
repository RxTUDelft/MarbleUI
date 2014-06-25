package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.StringMarbleModel;
import rx.Observable;
import rx.Scheduler;
import rx.observables.MathObservable;

/**
 * Created by ferdy on 6/25/14.
 */
public class BootstrapMax extends BootstrapStringOperator {

    public BootstrapMax() {
        super("Max");
    }

    @Override
    public Observable<StringMarbleModel> call1(Scheduler s, Observable<StringMarbleModel> in1) {
        return MathObservable.max(in1
                .map(StringMarbleModel::getValue)
                .map(Integer::parseInt))
                .map(StringMarbleModel::new);
    }
}
