package org.rxtudelft.marbleui.diagram;

/**
 * Created by ferdy on 6/18/14.
 */
public class TimestampedObservableModelFactory extends ObservableModelFactory {

    public ObservableModel getOutObservable() {
        return new TimestampedObservableModel();
    }
}
