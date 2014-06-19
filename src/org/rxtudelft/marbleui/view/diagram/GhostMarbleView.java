package org.rxtudelft.marbleui.view.diagram;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import rx.observables.JavaFxObservable;

/**
 * Transparent marble
 */
public final class GhostMarbleView extends SimpleMarbleView {

    private IntegerProperty n;
    private ObjectProperty<Color> color;

    public GhostMarbleView(int n, double r) {
        super(n, r);

        this.n = new SimpleIntegerProperty(n);
        this.color = new SimpleObjectProperty<>(Color.TRANSPARENT);
        this.getP().setFill(Color.TRANSPARENT);
        this.getP().setStroke(Color.GRAY);
        this.getP().setStrokeWidth(2);

        JavaFxObservable.fromObservableValue(this.n).subscribe(newN -> {
            this.setPoints(newN.intValue());
        });

        JavaFxObservable.fromObservableValue(this.color).subscribe(newColor -> {
            this.getP().setFill(newColor);
        });
    }

    public int getN() {
        return n.get();
    }

    public IntegerProperty nProperty() {
        return n;
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }
}
