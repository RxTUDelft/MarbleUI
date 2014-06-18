package org.rxtudelft.marbleui.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableFloatValue;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import rx.observables.JavaFxObservable;

/**
 * Created by ferdy on 6/18/14.
 */
public class Slider extends Group {
    private DoubleProperty p;
    private Color c;

    public Slider(double w, double h, Color c) {
        Rectangle bg = new Rectangle(w, h);
        bg.setFill(Color.TRANSPARENT);
        this.getChildren().add(bg);

        Line hLine = new Line(0, h/2, w, h/2);
        hLine.setStrokeWidth(2);
        hLine.setStroke(Color.BLACK);
        this.getChildren().add(hLine);

        this.p = new SimpleDoubleProperty(0.5);

        Circle slider = new Circle(10);
        slider.setStrokeWidth(2);
        slider.setStroke(Color.BLACK);
        slider.setFill(c);
        slider.setTranslateX(w/2);
        slider.setTranslateY(h/2);
        this.getChildren().add(slider);

        JavaFxObservable.fromObservableValue(this.p).subscribe(p -> {
            slider.setTranslateX(w * this.limitP(p.doubleValue()));
        });

        JavaFxObservable.fromNodeEvents(this, MouseEvent.MOUSE_DRAGGED).subscribe(e -> {
            this.p.setValue(this.limitP(e.getX() / w));
        });
    }

    private double limitP(double p) {
        if (p < 0) {
            return 0;
        }

        else if( p > 1) {
            return 1;
        }

        else {
            return p;
        }
    }

    public double getP() {
        return p.get();
    }

    public DoubleProperty pProperty() {
        return p;
    }
}
