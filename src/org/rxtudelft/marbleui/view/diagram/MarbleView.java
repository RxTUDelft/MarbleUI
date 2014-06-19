package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.Group;
import javafx.scene.shape.Polygon;

/**
 * Abstract class for everything that can be a marble.
 * Can be simple or or composite
 */
public abstract class MarbleView extends Group {
    abstract Polygon getP();
}
