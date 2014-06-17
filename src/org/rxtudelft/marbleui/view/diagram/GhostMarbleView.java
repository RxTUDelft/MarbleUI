package org.rxtudelft.marbleui.view.diagram;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;
import rx.observables.JavaFxObservable;

/**
 * Transparent marble
 */
public final class GhostMarbleView extends SimpleMarbleView {

    private IntegerProperty n;

    public GhostMarbleView(int n, double r) {
        super(n, r);

        this.n = new SimpleIntegerProperty(n);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.GRAY);
        this.setStrokeWidth(2);

        JavaFxObservable.fromObservableValue(this.n).subscribe(newN -> {
            this.setPoints(newN.intValue());
        });
    }

    public int getN() {
        return n.get();
    }

    public IntegerProperty nProperty() {
        return n;
    }
}
