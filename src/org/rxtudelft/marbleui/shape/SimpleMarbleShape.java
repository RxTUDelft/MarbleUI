package org.rxtudelft.marbleui.shape;

import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;
import org.rxtudelft.marbleui.diagram.MarbleModel;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by ferdy on 5/22/14.
 */
public class SimpleMarbleShape extends MarbleShape {
    public MarbleModel model;

    public MarbleModel getModel() {
        return this.model;
    }

    public void setModel(MarbleModel model) {
        this.model = model;
    }

    public SimpleMarbleShape(MarbleModel model, double x, double y, int n, double r) {
        super();

        this.model = model;
        Polygon polygon = new Polygon();
        if (n <= 2) throw new IllegalArgumentException("n should be larger than 2");
        double t = 2 * PI / n;

        ObservableList<Double> points = polygon.getPoints();

        Stream.iterate(0, (m) -> m + 1).limit(n)
                .flatMapToDouble((i) -> DoubleStream.of(x + r * sin(t * i), y - r * cos(t * i)))
                .forEach(points::add);
    }
}
