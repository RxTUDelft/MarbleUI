package org.rxtudelft.marbleui.view.diagram.marble;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.rxtudelft.marbleui.diagram.ErrorModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;

/**
 * Created by jeff on 19-6-14.
 */
public class ErrorView extends SimpleMarbleView {
    private Polygon p;

    public ErrorView(double w, double h) {
        super(new ErrorModel(), w, h);
        this.p = new Polygon(
                 0, w,
                 0,-w,
                 0, 0,
                 h, 0,
                -h, 0,
                 0, 0
        );

        this.p.setFill(Color.TRANSPARENT);
        this.p.setStroke(Color.BLACK);
        this.p.setStrokeWidth(2);
        this.p.setRotate(45);
        this.p.setTranslateX(w/2);
        this.p.setTranslateY(h/2);
    }

    @Override
    public void turnGhost() {
        this.p.setStroke(Color.GRAY);
    }

    @Override
    public Node getNode() {
        return p;
    }

    @Override
    public ErrorModel getModel() {
        return new ErrorModel();
    }

}
