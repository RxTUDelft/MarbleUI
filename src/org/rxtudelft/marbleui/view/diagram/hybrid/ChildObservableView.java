package org.rxtudelft.marbleui.view.diagram.hybrid;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.rxtudelft.marbleui.diagram.*;
import org.rxtudelft.marbleui.view.diagram.marble.CompletedView;
import org.rxtudelft.marbleui.view.diagram.marble.ErrorView;
import org.rxtudelft.marbleui.view.diagram.marble.MarbleView;
import org.rxtudelft.marbleui.view.diagram.marble.NGonMarbleView;
import org.rxtudelft.marbleui.view.diagram.observable.BaseObservableView;
import org.rxtudelft.marbleui.view.diagram.observable.NGonObservableView;

/**
 * Created by ferdy on 6/25/14.
 */
public class ChildObservableView extends NGonObservableView implements MarbleView {

    private double offset;

    public ChildObservableView(ObservableModel model, double w, double h, double r, double angle, double offset) {
        super(model, w, h, r);
        this.offset = offset;
        this.rotate(angle);
    }

    @Override
    public Line obsLine() {
        return new Line(0, 0, getWidth(), 0);
    }

    @Override
    public NGonMarbleView getMarbleView(MarbleModel m, double t, double w, double h) {
        return super.getMarbleView(m, t, w, h);
    }

    @Override
    public void turnGhost() {
        this.getLine().setStroke(Color.GRAY);
    }

    @Override
    public ChildObservableModel getModel() {
        return (ChildObservableModel) super.getModel();
    }
}
