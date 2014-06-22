package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.control.Label;
import org.rxtudelft.marbleui.diagram.TimestampedSimpleMarbleModel;

/**
 * Created by ferdy on 6/19/14.
 */
public class TimestampedSimpleMarbleView extends NGonMarbleView {
    public TimestampedSimpleMarbleView(TimestampedSimpleMarbleModel m, double r, long timeStamp) {
        super(m, r);
        this.getChildren().add(new Label("" + timeStamp));
    }
}
