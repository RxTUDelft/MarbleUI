package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by jeff on 19-6-14.
 */
public class SimpleCompletedView extends MarbleView {
    private Line line;
    private double radius;

    public SimpleCompletedView(double r) {
        this.line = new Line(0, -r, 0, r);
        this.radius = r;

        this.line.setStroke(Color.BLACK);
        this.line.setStrokeWidth(2);

        this.getChildren().add(line);
    }
}
