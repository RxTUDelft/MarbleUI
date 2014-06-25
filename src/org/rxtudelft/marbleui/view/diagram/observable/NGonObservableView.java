package org.rxtudelft.marbleui.view.diagram.observable;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.bootstrapOperator.NGonMarbleModel;

/**
 * Created by ferdy on 6/25/14.
 */
public class NGonObservableView extends BaseObservableView<NGonMarbleModel> {

    public NGonObservableView(ObservableModel model, double w, double h, double r) {
        super(model, w, h, r);
    }
}
