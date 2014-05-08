package org.rxtudelft.marbleui;

import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.lang.Math.*;

/**
 * Static methods for creating regular polygons
 */
public class NGon {
    /**
     * Creates a regular polygon of n sides that points to the right.
     *
     * @param x midpoint x coordinate
     * @param y midpoint y coordinate
     * @param n amount of sides > 2
     * @param r distance between the midpoint and a corner
     */
    public static Polygon pointRight(double x, double y, int n, double r) {
        Polygon polygon = new Polygon();
        if (n <= 2) throw new IllegalArgumentException("n should be larger than 2");
        double t = 2 * PI / n;

        ObservableList<Double> points = polygon.getPoints();

        Stream.iterate(0, (m) -> m + 1).limit(n)
                .flatMapToDouble((i) -> DoubleStream.of(x + r * cos(t * i), y + r * sin(t * i)))
                .forEach(points::add);

        return polygon;
    }
    /**
     * Creates a regular polygon of n sides that points to the right.
     *
     * @param x midpoint x coordinate
     * @param y midpoint y coordinate
     * @param n amount of sides > 2
     * @param r distance between the midpoint and a corner
     */
    public static Polygon pointUp(double x, double y, int n, double r) {
        Polygon polygon = new Polygon();
        if (n <= 2) throw new IllegalArgumentException("n should be larger than 2");
        double t = 2 * PI / n;

        ObservableList<Double> points = polygon.getPoints();

        Stream.iterate(0, (m) -> m + 1).limit(n)
                .flatMapToDouble((i) -> DoubleStream.of(x + r * sin(t * i), y - r * cos(t * i)))
                .forEach(points::add);

        return polygon;
    }
}
