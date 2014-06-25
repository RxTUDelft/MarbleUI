package org.rxtudelft.marbleui.view.diagram.marble;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;

/**
 * Abstract class for everything that can be a marble.
 * Can be simple or or composite
 */
public abstract class SimpleMarbleView implements MarbleView {
    private SimpleMarbleModel model;
    private double width;
    private double height;

    protected SimpleMarbleView(SimpleMarbleModel m, double w, double h) {
        this.model = m;
        this.width = w;
        this.height = h;
    }

    @Override
    public SimpleMarbleModel getModel() {
        return this.model;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
