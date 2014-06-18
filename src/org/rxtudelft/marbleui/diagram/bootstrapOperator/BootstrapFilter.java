package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModelFactory;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ferdy on 5/16/14.
 */
public class BootstrapFilter extends BootstrapOperator1<SimpleMarbleModel> {

    private Func1<SimpleMarbleModel, Boolean> filter;

    public BootstrapFilter(ObservableModelFactory obsModelFactory, Func1<SimpleMarbleModel, Boolean> filter) {
        super(obsModelFactory);
        this.filter = filter;
    }

    @Override
    public Observable<SimpleMarbleModel> call1(Observable<SimpleMarbleModel> toFilter) {
        return toFilter.filter(filter);
    }
}
