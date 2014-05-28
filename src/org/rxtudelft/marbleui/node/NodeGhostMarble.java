package org.rxtudelft.marbleui.node;

import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.node.NodeSimpleMarble;

/**
 * Transparent marble
 */
public final class NodeGhostMarble extends NodeSimpleMarble {
    public NodeGhostMarble(int n, double r) {
        super(n, r);

        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.GRAY);
        this.setStrokeWidth(2);
    }
}
