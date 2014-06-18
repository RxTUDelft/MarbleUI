package org.rxtudelft.marbleui.diagram;

/**
 * Created by ferdy on 6/18/14.
 */
public class ObservableModelFactory<T extends MarbleModel> {
    public ObservableModel<T> getOutObservable() {
        return new ObservableModel<T>();
    }
}
