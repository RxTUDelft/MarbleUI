package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.TimestampedObservableModel;

/**
 * Hacky timestamp. To store marbles use SimpleMarbleModels,
 * Timestamp is rendered through a specialized ObservableView and MarbleView
 */
public class BootstrapTimestamp extends BootstrapMap {
    public BootstrapTimestamp() {
        super(o -> o);
    }

    public TimestampedObservableModel getOutObservableModel() {
        return new TimestampedObservableModel();
    }

}