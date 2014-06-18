package org.rxtudelft.marbleui.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Created by ferdy on 6/18/14.
 */
public class DrawButton extends Group {
    public DrawButton(int w, int h) {
        Rectangle bg = new Rectangle(w, h);
        bg.setFill(Color.TRANSPARENT);
        this.getChildren().add(bg);
    }
}
