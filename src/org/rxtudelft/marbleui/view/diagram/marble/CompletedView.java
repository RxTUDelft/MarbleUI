package org.rxtudelft.marbleui.view.diagram.marble;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.rxtudelft.marbleui.diagram.CompletedModel;

/**
 * Created by jeff on 19-6-14.
 */
public class CompletedView extends SimpleMarbleView {
    private Polygon p;

    public CompletedView(double w, double h) {
        super(new CompletedModel(), w, h);
        this.p = new Polygon(0, -h, 0, h);

        this.p.setStroke(Color.BLACK);
        this.p.setStrokeWidth(2);
    }

    @Override
    public Node getNode() {
        return p;
    }

    @Override
    public CompletedModel getModel() {
        return new CompletedModel();
    }

    @Override
    public void turnGhost() {
        this.p.setStroke(Color.GRAY);
    }
}
