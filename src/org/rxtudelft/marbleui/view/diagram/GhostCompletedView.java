package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.paint.Color;

/**
 * Created by jeff on 19-6-14.
 */
public class GhostCompletedView extends SimpleCompletedView {
    public GhostCompletedView(double r) {
        super(r);

        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.GRAY);
        this.setStrokeWidth(2);
    }
}
