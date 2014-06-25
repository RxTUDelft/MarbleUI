package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.Node;

/**
 * Created by ferdy on 6/25/14.
 */
public interface View {
    public double getWidth();
    public double getHeight();
    public Object getModel();
    public Node getNode();
}
