package org.rxtudelft.marbleui.node;

/**
 * Created by ferdy on 5/22/14.
 */
public class ObservableMarbleShape extends MarbleShape {
    private ObservableShape observable;

    public ObservableMarbleShape(ObservableShape observable) {
        super();

        this.observable = observable;
    }
}
