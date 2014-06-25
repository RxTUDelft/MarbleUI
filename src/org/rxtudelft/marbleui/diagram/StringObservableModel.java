package org.rxtudelft.marbleui.diagram;

/**
 * Created by ferdy on 6/25/14.
 */
public class StringObservableModel extends ObservableModel {
    public StringObservableModel() {
        super();
        this.put(0, new StringMarbleModel(0));
    }
}
