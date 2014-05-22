package org.rxtudelft.marbleui.shape;

import javafx.scene.shape.Shape;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import rx.Observable;

/**
 * Created by ferdy on 5/22/14.
 */
public class ObservableShape extends Shape {

    private Observable<MarbleModel> model;

    public ObservableShape(Observable<MarbleModel> model) {
        this.model = model;
    }

    public Observable<MarbleModel> getModel() {
        return this.model;
    }

    public void setModel(Observable<MarbleModel> model) {
        this.model = model;
    }

    @Override
    public com.sun.javafx.geom.Shape impl_configShape() {
        return null;
    }
}
