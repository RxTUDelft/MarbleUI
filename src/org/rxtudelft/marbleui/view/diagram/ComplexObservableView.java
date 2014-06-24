package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.Node;
import org.rxtudelft.marbleui.diagram.ChildObservableModel;
import org.rxtudelft.marbleui.diagram.ComplexObservableModel;
import org.rxtudelft.marbleui.diagram.MarbleModel;

/**
 * Created by ferdy on 6/21/14.
 */
public class ComplexObservableView extends ObservableView {
    private long offset;
    public ComplexObservableView(ComplexObservableModel model, double width, double height) {
        super(model, width, height);
    }

    @Override
    protected ChildObservableView getMarbleView(Long t, MarbleModel m) {
        Node n;
        ChildObservableModel gm = (ChildObservableModel) m;
        return new ChildObservableView(gm, getR(), t.intValue(), getWidth(), 0.2);
    }

    @Override
    public ComplexObservableModel getModel() {
        return (ComplexObservableModel) super.getModel();
    }
}
