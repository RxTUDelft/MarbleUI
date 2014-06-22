package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.rxtudelft.marbleui.diagram.MarbleModel;

/**
 * Abstract class for everything that can be a marble.
 * Can be simple or or composite
 */
public abstract class SimpleMarbleView extends MarbleView {
    abstract Polygon getP();
    private double r;

    protected SimpleMarbleView(MarbleModel m, double r) {
        super(m);
        this.r = r;
    }

    public abstract MarbleModel getModel();

    public SimpleMarbleView turnGhost() {
        this.getP().setFill(Color.TRANSPARENT);
        this.getP().setStroke(Color.GRAY);
        this.getP().setStrokeWidth(2);

        return this;
    }

    public abstract SimpleMarbleView clone(double r);

    public double getRadius() {
        return this.r;
    }
}
