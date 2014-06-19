package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Created by jeff on 19-6-14.
 */
public class SimpleErrorView extends MarbleView {
    private double radius;
    private Polygon p;

    public SimpleErrorView(double r) {
        this.p = new Polygon( 0, 0,
                0, r,
                0, 0,
                r, 0,
                0, 0,
                0 -r,
                0, 0,
                -r, 0,
                0, 0 );
        this.radius = r;

        this.p.setStroke(Color.BLACK);
        this.p.setStrokeWidth(2);
        this.p.setRotate(45);

        this.getChildren().add(this.p);
    }

    @Override
    Polygon getP() {
        return p;
    }
}
