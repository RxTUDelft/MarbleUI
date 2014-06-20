package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.rxtudelft.marbleui.diagram.MarbleModel;

/**
 * Abstract class for everything that can be a marble.
 * Can be simple or or composite
 */
public abstract class MarbleView extends Group {
    abstract Polygon getP();
    public abstract MarbleModel getModel();

    public MarbleView turnGhost() {
        this.getP().setFill(Color.TRANSPARENT);
        this.getP().setStroke(Color.GRAY);
        this.getP().setStrokeWidth(2);

        return this;
    }

    public abstract MarbleView clone(double r);

    public abstract double getRadius();
}
