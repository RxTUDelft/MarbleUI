package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.StringMarbleModel;
import rx.Observable;
import rx.observables.StringObservable;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/25/14.
 */
public class BootstrapJoin extends BootstrapStringOperator {
    public BootstrapJoin() {
        super("Join");
    }

    @Override
    public Observable<StringMarbleModel> call1(TestScheduler s, Observable<StringMarbleModel> in1) {
        return StringObservable.join(in1.map(StringMarbleModel::getValue), " + ")
                .map(StringMarbleModel::new);
    }


}
