package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.Node;
import org.rxtudelft.marbleui.diagram.ChildObservableModel;
import org.rxtudelft.marbleui.diagram.MarbleModel;

/**
 * Created by ferdy on 6/21/14.
 */
public class ComplexObservableView extends ObservableView {
    private long offset;
    public ComplexObservableView(double width, double height) {
        super(width, height);
    }

    @Override
    protected Node getMarbleView(Long t, MarbleModel m) {
        Node n;
        ChildObservableModel gm = (ChildObservableModel) m;
        return new GroupedMarbleView(gm, getR(), t.intValue(), getWidth(), 0.2);
    }
}
