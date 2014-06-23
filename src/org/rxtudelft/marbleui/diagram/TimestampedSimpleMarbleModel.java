package org.rxtudelft.marbleui.diagram;

import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.bootstrapOperator.NGonMarbleModel;

/**
 * Created by ferdy on 6/19/14.
 */
public class TimestampedSimpleMarbleModel extends NGonMarbleModel {
    private long timeStamp;

    public TimestampedSimpleMarbleModel(int num, Color color, long timeStamp) {
        super(num, color);
        this.timeStamp = timeStamp;
    }
}
