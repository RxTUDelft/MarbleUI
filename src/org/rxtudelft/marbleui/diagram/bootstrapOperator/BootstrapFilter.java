package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

/**
 * Created by ferdy on 5/16/14.
 */
public class BootstrapFilter extends BootstrapOperator1<SimpleMarbleModel, SimpleMarbleModel> {

    private Func1<SimpleMarbleModel, Boolean> filter;

    public BootstrapFilter(Func1<SimpleMarbleModel, Boolean> filter) {
        this.filter = filter;
    }

    @Override
    public Observable< SimpleMarbleModel> call1(Scheduler s, Observable<SimpleMarbleModel> toFilter) {
        return toFilter.filter(filter);
    }
}
