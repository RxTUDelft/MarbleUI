package org.rxtudelft.marbleui.diagram;

/**
 * Created by ferdy on 6/21/14.
 */
public class ComplexObservableModelFactory extends ObservableModelFactory {
    public ObservableModel getOutObservable() {
        return new ComplexObservableModel();
    }

}
