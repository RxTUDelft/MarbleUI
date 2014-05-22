package org.rxtudelft.marbleui.shape;

import javafx.scene.shape.Shape;
import org.rxtudelft.marbleui.diagram.MarbleModel;

/**
 * Created by ferdy on 5/22/14.
 */
abstract public class MarbleShape extends Shape {
    public MarbleModel model;

    public MarbleShape(MarbleModel model) {
        this.model = model;
    }

    public MarbleModel getModel() {
        return this.model;
    }

    @Override
    public com.sun.javafx.geom.Shape impl_configShape() {
        return null;
    }
}
