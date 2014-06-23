package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;

/**
 * Created by ferdy on 6/24/14.
 */
public class NGonMarbleModel extends SimpleMarbleModel {

    private int num;
    private Color color;

    public NGonMarbleModel(int num, Color color) {
        super();
        this.color = color;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public Color getColor() {
        return color;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NGonMarbleModel that = (NGonMarbleModel) o;

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
