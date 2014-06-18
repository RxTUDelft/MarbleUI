package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.functions.Func1;

/**
 * Hacky timestamp. To store marbles use SimpleMarbleModels,
 * Timestamp is rendered through a specialized ObservableView and MarbleView
 */
public class BootstrapTimestamp extends BootstrapMap {

    private Func1<SimpleMarbleModel, SimpleMarbleModel> mapping;

    public BootstrapTimestamp() {
        super(o -> o);
        this.mapping = mapping;
    }

    @Override
    public Observable<SimpleMarbleModel> call1(Observable<SimpleMarbleModel> toMap) {
        return toMap.map(mapping);
    }

}