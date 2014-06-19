package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.paint.Color;

/**
 * Created by jeff on 19-6-14.
 */
public final class GhostErrorView extends SimpleErrorView {
    public GhostErrorView(double r) {
        super(r);

        this.getP().setFill(Color.TRANSPARENT);
        this.getP().setStroke(Color.GRAY);
        this.getP().setStrokeWidth(2);
    }
}
