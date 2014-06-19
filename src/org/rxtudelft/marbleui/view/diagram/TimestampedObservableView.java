package org.rxtudelft.marbleui.view.diagram;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;

/**
 * Created by ferdy on 6/19/14.
 */
public class TimestampedObservableView extends ObservableView<SimpleMarbleModel> {
    public TimestampedObservableView(double width, double height) {
        super(width, height);
    }

    @Override
    protected MarbleView getMarble(MarbleModel sm, double r, long t) {
        return new TimestampedSimpleMarbleView(((SimpleMarbleModel)sm).getNum(), r, t);
    }
}
