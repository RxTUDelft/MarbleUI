package org.rxtudelft.marbleui.view.diagram;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.lang.Math.*;

/**
 * Jeff's awesome NGon used as a simple marble
 */
public class SimpleMarbleView extends MarbleView {

    private double r;
    private Polygon p;

    public SimpleMarbleView(int n, double r) {
        super();
        this.r = r;
        this.p = new Polygon();
        if (n <= 2) throw new IllegalArgumentException("n should be larger than 2");

        this.setPoints(n);

        this.p.setFill(Color.RED);
        this.p.setStroke(Color.BLACK);
        this.p.setStrokeWidth(2);

        this.getChildren().add(this.p);
    }

    protected void setPoints(int n) {

        double t = 2 * PI / n;
        ObservableList<Double> points = this.p.getPoints();
        points.clear();

        Stream.iterate(0, (m) -> m + 1).limit(n)
                .flatMapToDouble((i) -> DoubleStream.of(r * sin(t * i), r * cos(t * i)))
                .forEach(points::add);
    }

    @Override
    Polygon getP() {
        return p;
    }
}
