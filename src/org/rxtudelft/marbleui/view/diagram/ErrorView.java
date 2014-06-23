package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.rxtudelft.marbleui.diagram.SimpleErrorModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;

/**
 * Created by jeff on 19-6-14.
 */
public class ErrorView extends SimpleMarbleView {
    private Polygon p;

    public ErrorView(double r) {
        super(new SimpleErrorModel(), r);
        this.p = new Polygon(
                 0, r,
                 0,-r,
                 0, 0,
                 r, 0,
                -r, 0,
                 0, 0
        );

        this.p.setFill(Color.TRANSPARENT);
        this.p.setStroke(Color.BLACK);
        this.p.setStrokeWidth(2);
        this.p.setRotate(45);

        this.getChildren().add(this.p);
    }

    @Override
    Polygon getP() {
        return p;
    }

    @Override
    public SimpleMarbleModel getModel() {
        return new SimpleErrorModel();
    }

    @Override
    public ErrorView clone() {
        return new ErrorView(getRadius());
    }
}
