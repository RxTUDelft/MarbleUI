package org.rxtudelft.marbleui.view.diagram.observable;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.NGonMarbleModel;
import org.rxtudelft.marbleui.view.diagram.marble.NGonMarbleView;

/**
 * Created by ferdy on 6/25/14.
 */
public class NGonObservableView extends BaseObservableView<NGonMarbleModel> {

    public NGonObservableView(ObservableModel model, double w, double h, double r) {
        super(model, w, h, r);
    }

    @Override
    public NGonMarbleView getMarbleView(MarbleModel m, double t, double w, double h) {
        return new NGonMarbleView((NGonMarbleModel) m, w, h);
    }
}
