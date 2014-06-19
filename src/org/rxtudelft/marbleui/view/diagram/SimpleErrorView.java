package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Created by jeff on 19-6-14.
 */
public class SimpleErrorView extends MarbleView {
    private double radius;
    private Polygon x;

    public SimpleErrorView(double r) {
        this.x = new Polygon( 0, 0,
                0, r,
                0, 0,
                r, 0,
                0, 0,
                0 -r,
                0, 0,
                -r, 0,
                0, 0 );
        this.radius = r;

        this.x.setStroke(Color.BLACK);
        this.x.setStrokeWidth(2);
        this.x.setRotate(45);

        this.getChildren().add(this.x);
    }

    public void setFill(Color color) {
        // stubbed for ease of use
    }
}
