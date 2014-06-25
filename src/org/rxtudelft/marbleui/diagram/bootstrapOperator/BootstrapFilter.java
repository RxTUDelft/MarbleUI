package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.NGonMarbleModel;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 5/16/14.
 */
public class BootstrapFilter extends BootstrapOperator1<NGonMarbleModel, NGonMarbleModel> {

    private Func1<NGonMarbleModel, Boolean> filter;

    public BootstrapFilter(Func1<NGonMarbleModel, Boolean> filter) {
        super("Filter");
        this.filter = filter;
    }

    @Override
    public Observable< NGonMarbleModel> call1(TestScheduler s, Observable<NGonMarbleModel> toFilter) {
        return toFilter.filter(filter);
    }
}
