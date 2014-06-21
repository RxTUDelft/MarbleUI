package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.Node;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;

/**
 * Created by ferdy on 6/19/14.
 */
public class TimestampedObservableView extends ObservableView {
    public TimestampedObservableView(double width, double height) {
        super(width, height);
    }

    @Override
    protected Node getMarbleView(Long t, MarbleModel m) {
        return new TimestampedSimpleMarbleView(((SimpleMarbleModel)m).getNum(), getR(), t);
    }
}
