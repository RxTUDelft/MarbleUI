package org.rxtudelft.marbleui.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import rx.Observable;
import rx.observables.JavaFxObservable;

/**
 * Created by ferdy on 6/18/14.
 */
public class ColorPicker extends Group {
    private Slider r;
    private Slider g;
    private Slider b;

    private ObjectProperty<Color> color;

    public ColorPicker(double w, double h) {
        this.r = new Slider(w, h, Color.RED);
        this.g = new Slider(w, h, Color.GREEN);
        this.b = new Slider(w, h, Color.BLUE);

        this.color = new SimpleObjectProperty<>(Color.RED);

        VBox root = new VBox();
        root.getChildren().add(r);
        root.getChildren().add(g);
        root.getChildren().add(b);

        this.getChildren().add(root);

        Observable<Number> rVal = JavaFxObservable.fromObservableValue(this.r.pProperty());
        Observable<Number> gVal = JavaFxObservable.fromObservableValue(this.g.pProperty());
        Observable<Number> bVal = JavaFxObservable.fromObservableValue(this.b.pProperty());

        Observable.combineLatest(rVal, gVal, bVal, (r, g, b) -> {
            return Color.rgb(
                    colorToInt(r.doubleValue()),
                    colorToInt(g.doubleValue()),
                    colorToInt(b.doubleValue())
            );
        }).subscribe(c -> {
            ColorPicker.this.color.setValue(c);
        });
    }

    public ObjectProperty<Color> getColor() {
        return color;
    }

    public static int colorToInt(double color) {
        return (int)Math.min(255, Math.floor(255 * color));
    }
}
