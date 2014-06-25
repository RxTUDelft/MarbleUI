package org.rxtudelft.marbleui.view.diagram.marble;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.rxtudelft.marbleui.diagram.CompletedModel;

/**
 * Created by jeff on 19-6-14.
 */
public class CompletedView extends SimpleMarbleView {
    private Polygon p;
    private double radius;

    public CompletedView(double r) {
        super(new CompletedModel(), r);
        this.p = new Polygon(0, -r, 0, r);
        this.radius = r;

        this.p.setStroke(Color.BLACK);
        this.p.setStrokeWidth(2);

        this.getChildren().add(p);
    }

    @Override
    Polygon getP() {
        return p;
    }

    @Override
    public CompletedModel getModel() {
        return new CompletedModel();
    }

    @Override
    public CompletedView clone() {
        return new CompletedView(getRadius());
    }

    @Override
    public double getRadius() {
        return radius;
    }
}
