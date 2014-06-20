package org.rxtudelft.marbleui.diagram;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Created by ferdy on 5/28/14.
 */
public class SimpleMarbleModel implements MarbleModel {

    private int num;
    private Color color;

    public SimpleMarbleModel(int num, Color color) {
        super();
        this.color = color;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public Paint getColor() {
        return color;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleMarbleModel that = (SimpleMarbleModel) o;

        if (num != that.num) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = num;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
