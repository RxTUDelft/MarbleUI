package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.Node;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import org.rxtudelft.marbleui.diagram.TimestampedObservableModel;
import org.rxtudelft.marbleui.diagram.TimestampedSimpleMarbleModel;

/**
 * Created by ferdy on 6/19/14.
 */
public class TimestampedObservableView extends ObservableView {
    public TimestampedObservableView(TimestampedObservableModel model, double width, double height) {
        super(model, width, height);
    }

    @Override
    protected TimestampedSimpleMarbleView getMarbleView(Long t, MarbleModel m) {
        return new TimestampedSimpleMarbleView((TimestampedSimpleMarbleModel)m, getR(), t);
    }

    @Override
    public TimestampedObservableModel getModel() {
        return (TimestampedObservableModel) super.getModel();
    }
}
