package org.rxtudelft.marbleui.view.diagram;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import org.rxtudelft.marbleui.diagram.bootstrapOperator.NGonMarbleModel;
import rx.observables.JavaFxObservable;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.lang.Math.*;

/**
 * Jeff's awesome NGon used as a simple marble
 */
public class NGonMarbleView extends SimpleMarbleView {

    protected IntegerProperty n;
    protected ObjectProperty<Color> color;
    private Polygon p;

    public NGonMarbleView(NGonMarbleModel m, double r) {
        super(m, r);
        int n = m.getNum();
        this.p = new Polygon();
        if (n <= 2) throw new IllegalArgumentException("n should be larger than 2");

        this.setPoints(n);

        this.p.setFill(m.getColor());
        this.p.setStroke(Color.BLACK);
        this.p.setStrokeWidth(2);

        this.getChildren().add(this.p);
        this.n = new SimpleIntegerProperty(n);
        this.color = new SimpleObjectProperty<>(m.getColor());

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
                .flatMapToDouble((i) -> DoubleStream.of(getRadius() * sin(t * i), getRadius() * cos(t * i)))
                .forEach(points::add);
    }

    @Override
    public Polygon getP() {
        return p;
    }

    @Override
    public NGonMarbleModel getModel() {
        return new NGonMarbleModel(n.get(), color.get());
    }

    @Override
    public SimpleMarbleView clone() {
        NGonMarbleView clone = new NGonMarbleView(this.getModel(), getRadius());
        clone.color.setValue(this.color.getValue());
        return clone;
    }

    public IntegerProperty nProperty() {
        return n;
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }
}
