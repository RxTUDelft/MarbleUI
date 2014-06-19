package org.rxtudelft.marbleui.diagram;

import javafx.scene.paint.Color;

/**
 * Created by ferdy on 6/19/14.
 */
public class TimestampedSimpleMarbleModel extends SimpleMarbleModel {
    private long timeStamp;

    public TimestampedSimpleMarbleModel(int num, Color color, long timeStamp) {
        super(num, color);
        this.timeStamp = timeStamp;
    }
}
