package org.rxtudelft.marbleui.view.diagram.marble;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.rxtudelft.marbleui.diagram.NGonMarbleModel;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.lang.Math.*;

/**
 * Jeff's awesome NGon used as a simple marble
 */
public class NGonMarbleView extends SimpleMarbleView {
    private Polygon p;

    public NGonMarbleView(NGonMarbleModel m, double w, double h) {
        super(m, w, h);
        this.p = new Polygon();
        if (m.getNum() <= 2) throw new IllegalArgumentException("n should be larger than 2");

        //make view as model dictates
        this.p.setFill(m.getColor());
        this.p.setStroke(Color.BLACK);
        this.p.setStrokeWidth(2);

        this.setPoints(m.getNum());

        m.colorProperty().addListener((p, o, n) -> {
            ((Polygon)NGonMarbleView.this.getNode()).setFill(n);
        });
        m.numProperty().addListener((p, o, n) -> {
            NGonMarbleView.this.setPoints(n.intValue());
        });
    }

    protected void setPoints(int n) {
        double t = 2 * PI / n;
        ObservableList<Double> points = this.p.getPoints();
        points.clear();

        Stream.iterate(0, (m) -> m + 1).limit(n)
                .flatMapToDouble((i) -> DoubleStream.of(getRadius() * sin(t * i), getRadius() * cos(t * i)))
                .forEach(points::add);
    }

    @Override
    public NGonMarbleModel getModel() {
        return (NGonMarbleModel) super.getModel();
    }

    @Override
    public Node getNode() {
        return this.p;
    }

    @Override
    public void turnGhost() {
        this.p.setFill(Color.GRAY);
    }

    public double getRadius() {
        return Math.min(getWidth(), getHeight());
    }
}
