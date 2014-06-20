package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.ObservableModelFactory;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import org.rxtudelft.marbleui.diagram.TimestampedObservableModelFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Hacky timestamp. To store marbles use SimpleMarbleModels,
 * Timestamp is rendered through a specialized ObservableView and MarbleView
 */
public class BootstrapTimestamp extends BootstrapMap {
    public BootstrapTimestamp() {
        super(o -> o);
    }

    public TimestampedObservableModelFactory getOutObservableModelFactory() {
        return new TimestampedObservableModelFactory();
    }

}