package org.rxtudelft.marbleui.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import rx.observables.JavaFxObservable;


/**
 * Created by ferdy on 6/17/14.
 */
public class Counter extends HBox {

    class DecButton extends Group {
        public DecButton() {
            this(50, 50);
        }
        public DecButton(int w, int h) {
            Rectangle bg = new Rectangle();
            bg.setFill(Color.TRANSPARENT);
            this.getChildren().add(bg);
            Line hLine = new Line(0, h/2, w, h/2);
            hLine.setStrokeWidth(2);
            this.getChildren().add(hLine);
        }
    }

    class IncButton extends Group {
        public IncButton() {
            this(50, 50);
        }
        public IncButton(int w, int h) {
            Rectangle bg = new Rectangle(w, h);
            bg.setFill(Color.TRANSPARENT);
            this.getChildren().add(bg);
            Line hLine = new Line(0, h/2, w, h/2);
            hLine.setStrokeWidth(2);
            Line vLine = new Line(w/2, 0, w/2, h);
            vLine.setStrokeWidth(2);

            this.getChildren().add(hLine);
            this.getChildren().add(vLine);
        }
    }

    private IntegerProperty i;

    private Node min;
    private Node plus;

    private Label numLabel;

    public Counter(int i) {

        this.i = new SimpleIntegerProperty(i);

        this.min = new DecButton();
        this.plus = new IncButton();
        this.numLabel = new Label("" + this.i.get());
        this.numLabel.setPrefWidth(50);

        this.getChildren().add(min);
        this.getChildren().add(numLabel);
        this.getChildren().add(plus);

        JavaFxObservable.fromNodeEvents(this.min, MouseEvent.MOUSE_CLICKED).subscribe(c -> {
            int iCurrent = Counter.this.i.get();
            if(iCurrent > 2) {
                Counter.this.i.setValue(iCurrent - 1);
                Counter.this.updateLabel();
            }
        });

        JavaFxObservable.fromNodeEvents(this.plus, MouseEvent.MOUSE_CLICKED).subscribe(c -> {
            Counter.this.i.setValue(Counter.this.i.get() + 1);
            Counter.this.updateLabel();
        });
    }

    private void updateLabel() {
        this.numLabel.setText("" + this.i.get());
    }
}
