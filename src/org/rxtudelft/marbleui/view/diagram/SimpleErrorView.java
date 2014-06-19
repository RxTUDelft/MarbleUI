package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by jeff on 19-6-14.
 */
public class SimpleErrorView extends MarbleView {
    private double radius;

    public SimpleErrorView(double r) {
        super();
        this.radius = r;

        Line l = new Line(0, -radius, 0, radius);
        l.setStroke(Color.BLACK);
        l.setStrokeWidth(2);
        l.setRotate(45);
        this.getChildren().add(l);
        l = new Line(0, -radius, 0, radius);
        l.setStroke(Color.BLACK);
        l.setStrokeWidth(2);
        l.setRotate(-45);
        this.getChildren().add(l);
    }

    public void setFill(Color color) {
        // stubbed for ease of use
    }
}
