package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleErrorModel;

/**
 * Created by jeff on 19-6-14.
 */
public class SimpleErrorView extends MarbleView {
    private double radius;
    private Polygon p;

    public SimpleErrorView(double r) {
        this.p = new Polygon(
                 0, r,
                 0,-r,
                 0, 0,
                 r, 0,
                -r, 0,
                 0, 0
        );
        this.radius = r;

        this.p.setFill(Color.TRANSPARENT);
        this.p.setStroke(Color.BLACK);
        this.p.setStrokeWidth(2);
        this.p.setRotate(45);

        this.getChildren().add(this.p);
    }

    @Override
    Polygon getP() {
        return p;
    }

    @Override
    public MarbleModel getModel() {
        return new SimpleErrorModel();
    }

    @Override
    public MarbleView clone(double r) {
        return new SimpleErrorView(r);
    }

    @Override
    public double getRadius() {
        return radius;
    }
}
