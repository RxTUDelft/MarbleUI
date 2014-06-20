package org.rxtudelft.marbleui.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;


/**
 * Created by ferdy on 6/17/14.
 */
public class Counter extends HBox {
    private DrawButton decButton() {
        return decButton(50, 50);
    }

    private DrawButton decButton(int w, int h) {
        Line hLine = new Line(0, h/2, w, h/2);
        hLine.setStrokeWidth(2);

        return new DrawButton(w, h, hLine);
    }

    private DrawButton incButton() {
        return incButton(50, 50);
    }

    private DrawButton incButton(int w, int h) {
        Line hLine = new Line(0, h/2, w, h/2);
        hLine.setStrokeWidth(2);
        Line vLine = new Line(w/2, 0, w/2, h);
        vLine.setStrokeWidth(2);

        return new DrawButton(w, h, hLine, vLine);
    }

    private IntegerProperty i;

    private DrawButton min;
    private DrawButton plus;

    private Label numLabel;

    public Counter(int i) {

        this.i = new SimpleIntegerProperty(i);

        this.min = decButton();
        this.plus = incButton();
        this.numLabel = new Label("" + this.i.get());
        this.numLabel.setPrefWidth(50);

        this.getChildren().add(min);
        this.getChildren().add(numLabel);
        this.getChildren().add(plus);

        this.min.clickObs.subscribe(c -> {
            int iCurrent = Counter.this.i.get();
            if(iCurrent > 3) {
                Counter.this.i.setValue(iCurrent - 1);
                Counter.this.updateLabel();
            }
        });

        this.plus.clickObs.subscribe(c -> {
            Counter.this.i.setValue(Counter.this.i.get() + 1);
            Counter.this.updateLabel();
        });
    }

    private void updateLabel() {
        this.numLabel.setText("" + this.i.get());
    }

    public int getI() {
        return i.get();
    }

    public IntegerProperty iProperty() {
        return i;
    }
}
