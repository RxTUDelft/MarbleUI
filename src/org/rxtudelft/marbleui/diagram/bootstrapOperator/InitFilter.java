package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ferdy on 5/16/14.
 */
public class InitFilter implements BootstrapOperator1 {

    private Func1<MarbleModel, Boolean> filter;

    public InitFilter(Func1<MarbleModel, Boolean> filter) {
        this.filter = filter;
    }

    @Override
    public Observable<MarbleModel> call1(Observable<MarbleModel> toFilter) {
        return toFilter.filter(filter);
    }
}
