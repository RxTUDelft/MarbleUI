package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Created by jeff on 19-6-14.
 */
public class SimpleCompletedView extends MarbleView {
    private Polygon p;
    private double radius;

    public SimpleCompletedView(double r) {
        this.p = new Polygon(0, -r, 0, r);
        this.radius = r;

        this.p.setStroke(Color.BLACK);
        this.p.setStrokeWidth(2);

        this.getChildren().add(p);
    }

    @Override
    Polygon getP() {
        return p;
    }
}
