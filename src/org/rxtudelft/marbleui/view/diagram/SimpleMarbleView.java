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
public class SimpleMarbleView extends Polygon implements MarbleView {
    private double r;
    public SimpleMarbleView(int n, double r) {
        super();
        this.r = r;
        if (n <= 2) throw new IllegalArgumentException("n should be larger than 2");

        this.setPoints(n);

        this.setFill(Color.RED);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(2);
    }

    protected void setPoints(int n) {

        double t = 2 * PI / n;
        ObservableList<Double> points = this.getPoints();
        points.clear();

        Stream.iterate(0, (m) -> m + 1).limit(n)
                .flatMapToDouble((i) -> DoubleStream.of(r * sin(t * i), r * cos(t * i)))
                .forEach(points::add);
    }
}
