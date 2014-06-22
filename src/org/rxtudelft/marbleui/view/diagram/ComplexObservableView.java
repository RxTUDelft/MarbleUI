package org.rxtudelft.marbleui.view.diagram;

import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.rxtudelft.marbleui.diagram.GroupedObservableModel;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;

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
        GroupedObservableModel gm = (GroupedObservableModel) m;
        return new GroupedMarbleView(gm, getR(), t.intValue(), getWidth(), 0.2);
    }
}
