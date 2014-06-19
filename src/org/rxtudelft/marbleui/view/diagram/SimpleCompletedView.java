package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by jeff on 19-6-14.
 */
public class SimpleCompletedView extends Line implements MarbleView {
    private double radius;

    public SimpleCompletedView(double r) {
        super(0, -r, 0, r);
        this.radius = r;

        this.setStroke(Color.BLACK);
        this.setStrokeWidth(2);
    }
}
