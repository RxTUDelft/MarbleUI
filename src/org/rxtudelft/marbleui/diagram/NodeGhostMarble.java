package org.rxtudelft.marbleui.diagram;

import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.node.NodeSimpleMarble;

/**
 * Transparent marble
 */
public final class NodeGhostMarble extends NodeSimpleMarble {
    public NodeGhostMarble(int n, double r) {
        super(n, r);
        this.getStroke().setStroke(Color.GRAY);
        this.getStroke().setFill(Color.TRANSPARENT);
    }
}
