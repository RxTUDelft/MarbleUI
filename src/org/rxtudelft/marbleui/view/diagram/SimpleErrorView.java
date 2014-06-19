package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Created by jeff on 19-6-14.
 */
public class SimpleErrorView extends Polygon implements MarbleView {
    private double radius;

    public SimpleErrorView(double r) {
        super( 0, 0,
               0, r,
               0, 0,
               r, 0,
               0, 0,
               0 -r,
               0, 0,
              -r, 0,
               0, 0 );
        this.radius = r;

        this.setStroke(Color.BLACK);
        this.setStrokeWidth(2);
        this.setRotate(45);
    }

    public void setFill(Color color) {
        // stubbed for ease of use
    }
}
