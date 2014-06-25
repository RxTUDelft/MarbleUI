package org.rxtudelft.marbleui.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.StringObservableModel;
import org.rxtudelft.marbleui.view.diagram.marble.MarbleView;

import java.security.Key;

/**
 * Created by jeff on 19-6-14.
 */
public class ModePicker extends HBox {
    private ObjectProperty<MarbleModel> current;

    public static IntegerProperty corners;
    public static ObjectProperty<Color> color;

    public ModePicker(MarbleView first, MarbleView... marbleViews) {

        //setup colorpicker
        ColorPicker colorPicker = new ColorPicker(150, 45);
        this.getChildren().add(colorPicker);
        this.color = colorPicker.getColor();
        Counter sidesCounter = new Counter(5);
        this.getChildren().add(sidesCounter);
        this.corners = sidesCounter.iProperty();

        first.turnGhost();
        DrawButton b = new DrawButton(50, 50, first.getNode());
        b.clickObs.subscribe(c -> current.setValue(first.getModel()));

        this.getChildren().add(b);
        current = new SimpleObjectProperty<>(first.getModel());

        for (MarbleView marbleView : marbleViews) {
            marbleView.turnGhost();
            b = new DrawButton(50, 50, marbleView.getNode());
            b.clickObs.subscribe(c -> current.setValue(marbleView.getModel()));

            if(marbleView instanceof StringObservableModel) {
                ((TextArea)b.getChildren().get(0)).addEventHandler(KeyEvent.ANY, e -> {
                    System.out.println(e);
                });
            }
            this.getChildren().add(b);
        }
    }

    public ObjectProperty<MarbleModel> modeProperty() {
        return current;
    }

    public static int getCorners() {
        return corners.get();
    }

    public static IntegerProperty cornersProperty() {
        return corners;
    }

    public static Color getColor() {
        return color.get();
    }

    public static ObjectProperty<Color> colorProperty() {
        return color;
    }
}
