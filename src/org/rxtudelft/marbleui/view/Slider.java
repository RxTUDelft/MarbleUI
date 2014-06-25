package org.rxtudelft.marbleui.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
    public static final int circleRadius = 10;
    public static final int strokeWidth = 2;
    public static final int pad = circleRadius + strokeWidth;

    public Slider(double w, double h, Color c) {
        Rectangle bg = new Rectangle(w + 2 * pad, h);
        bg.setFill(Color.TRANSPARENT);
        this.getChildren().add(bg);

        Line hLine = new Line(0, h/2, w, h/2);
        hLine.setTranslateX(pad);
        hLine.setStrokeWidth(strokeWidth);
        hLine.setStroke(Color.BLACK);
        this.getChildren().add(hLine);

        this.p = new SimpleDoubleProperty(0.5);

        Circle slider = new Circle(circleRadius);
        slider.setStrokeWidth(strokeWidth);
        slider.setStroke(Color.BLACK);
        slider.setFill(c);
        slider.setTranslateX(pad + w/2);
        slider.setTranslateY(h/2);
        this.getChildren().add(slider);

        JavaFxObservable.fromObservableValue(this.p).subscribe(p -> {
            slider.setTranslateX(pad + w * Slider.limitP(p.doubleValue()));
        });

        JavaFxObservable.fromNodeEvents(this, MouseEvent.MOUSE_DRAGGED).subscribe(e -> {
            this.p.setValue(Slider.limitP((e.getX()-pad) / w));
        });
    }

    private static double limitP(double p) {
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
