package org.rxtudelft.marbleui.view.diagram.observable;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.StringMarbleModel;
import org.rxtudelft.marbleui.view.diagram.marble.StringMarbleView;

/**
 * Created by ferdy on 6/25/14.
 */
public class StringObservableView extends BaseObservableView<StringMarbleModel> {

    public StringObservableView(ObservableModel model, double w, double h, double r) {
        super(model, w, h, r);
    }

    @Override
    public StringMarbleView getMarbleView(MarbleModel m, double t, double w, double h) {
        return new StringMarbleView((StringMarbleModel) m, w, h);
    }
}
