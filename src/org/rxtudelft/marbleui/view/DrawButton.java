package org.rxtudelft.marbleui.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import rx.Observable;
import rx.observables.JavaFxObservable;

/**
 * Created by ferdy on 6/18/14.
 */
public class DrawButton extends Group {
    public final Observable<? extends MouseEvent> clickObs = JavaFxObservable.fromNodeEvents(this, MouseEvent.MOUSE_CLICKED);

    public DrawButton(double w, double h, Node... nodes) {
        Rectangle bg = new Rectangle(w, h);
        bg.setFill(Color.TRANSPARENT);
        this.getChildren().add(bg);
        this.getChildren().addAll(nodes);
    }
}
