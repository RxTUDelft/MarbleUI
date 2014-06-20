package org.rxtudelft.marbleui.view.diagram;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.observables.JavaFxObservable;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.lang.Math.*;

/**
 * Jeff's awesome NGon used as a simple marble
 */
public class SimpleMarbleView extends MarbleView {

    protected IntegerProperty n;
    protected ObjectProperty<Color> color;
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
        this.n = new SimpleIntegerProperty(n);
        this.color = new SimpleObjectProperty<>(Color.TRANSPARENT);

        JavaFxObservable.fromObservableValue(this.nProperty()).subscribe(newN -> {
            this.setPoints(newN.intValue());
        });

        JavaFxObservable.fromObservableValue(this.colorProperty()).subscribe(newColor -> {
            this.getP().setFill(newColor);
        });
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

    @Override
    public MarbleModel getModel() {
        return new SimpleMarbleModel(n.get(), color.get());
    }

    @Override
    public MarbleView clone(double r) {
        SimpleMarbleView clone = new SimpleMarbleView(n.get(), r);
        clone.color.setValue(this.color.getValue());
        return clone;
    }

    @Override
    public double getRadius() {
        return r;
    }

    public IntegerProperty nProperty() {
        return n;
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }
}
