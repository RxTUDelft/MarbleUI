package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.control.Label;

/**
 * Created by ferdy on 6/19/14.
 */
public class TimestampedSimpleMarbleView extends SimpleMarbleView {
    public TimestampedSimpleMarbleView(int n, double r, long timeStamp) {
        super(n, r);
        this.getChildren().add(new Label("" + timeStamp));
    }
}
