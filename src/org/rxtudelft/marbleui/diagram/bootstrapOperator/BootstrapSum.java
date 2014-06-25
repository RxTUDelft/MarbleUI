package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.StringMarbleModel;
import rx.Observable;
import rx.Scheduler;
import rx.observables.MathObservable;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/25/14.
 */
public class BootstrapSum extends BootstrapStringOperator {

    public BootstrapSum() {
        super("Max");
    }

    @Override
    public Observable<StringMarbleModel> call1(TestScheduler s, Observable<StringMarbleModel> in1) {
        return MathObservable.sumDouble(in1
                .map(StringMarbleModel::getValue)
                .map(Double::parseDouble))
                .map(StringMarbleModel::new);
    }
}
