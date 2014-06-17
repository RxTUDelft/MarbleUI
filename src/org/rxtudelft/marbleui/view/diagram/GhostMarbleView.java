package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.paint.Color;

/**
 * Transparent marble
 */
public final class GhostMarbleView extends SimpleMarbleView {
    public GhostMarbleView(int n, double r) {
        super(n, r);

        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.GRAY);
        this.setStrokeWidth(2);
    }
}
