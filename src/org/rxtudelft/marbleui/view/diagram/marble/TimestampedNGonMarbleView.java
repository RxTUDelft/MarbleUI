package org.rxtudelft.marbleui.view.diagram.marble;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.rxtudelft.marbleui.diagram.NGonMarbleModel;

/**
 * Created by ferdy on 6/25/14.
 */
public class TimestampedNGonMarbleView extends NGonMarbleView {
    private double timestamp;
    private Group root;
    public TimestampedNGonMarbleView(NGonMarbleModel m, double w, double h, double t) {
        super(m, w, h);
        this.root = new Group();
        this.root.getChildren().add(super.getNode());
        this.root.getChildren().add(new Label("" + t));
    }

    @Override
    public Node getNode() {
        return this.root;
    }

}
