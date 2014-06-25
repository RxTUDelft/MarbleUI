package org.rxtudelft.marbleui.view.diagram.observable;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.NGonMarbleModel;
import org.rxtudelft.marbleui.view.diagram.marble.NGonMarbleView;
import org.rxtudelft.marbleui.view.diagram.marble.TimestampedNGonMarbleView;

/**
 * Created by ferdy on 6/25/14.
 */
public class TimestampedObservableView extends NGonObservableView {
    public TimestampedObservableView(ObservableModel model, double w, double h, double r) {
        super(model, w, h, r);
        System.out.println("obsV");
    }

    @Override
    public NGonMarbleView getMarbleView(MarbleModel m, double t, double w, double h) {
        return new TimestampedNGonMarbleView((NGonMarbleModel) m, w, h, t);
    }
}
