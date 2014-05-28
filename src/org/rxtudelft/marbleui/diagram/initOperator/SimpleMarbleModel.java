package org.rxtudelft.marbleui.diagram.initOperator;

import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.MarbleModel;

/**
 * Created by ferdy on 5/28/14.
 */
public class SimpleMarbleModel extends MarbleModel{

    private int num;
    private Color color;

    public SimpleMarbleModel(double t, int num, Color color) {
        super(t);
        this.color = color;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public Color getColor() {
        return color;
    }
}
