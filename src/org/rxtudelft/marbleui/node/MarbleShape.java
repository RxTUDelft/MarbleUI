package org.rxtudelft.marbleui.node;

import javafx.scene.shape.Shape;

/**
 * Created by ferdy on 5/22/14.
 */
abstract public class MarbleShape extends Shape {

    @Override
    public com.sun.javafx.geom.Shape impl_configShape() {
        return null;
    }
}
