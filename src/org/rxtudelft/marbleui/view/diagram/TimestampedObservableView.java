package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.Node;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import org.rxtudelft.marbleui.diagram.TimestampedSimpleMarbleModel;

/**
 * Created by ferdy on 6/19/14.
 */
public class TimestampedObservableView extends ObservableView {
    public TimestampedObservableView(double width, double height) {
        super(width, height);
    }

    @Override
    protected TimestampedSimpleMarbleView getMarbleView(Long t, MarbleModel m) {
        return new TimestampedSimpleMarbleView((TimestampedSimpleMarbleModel)m, getR(), t);
    }
}
