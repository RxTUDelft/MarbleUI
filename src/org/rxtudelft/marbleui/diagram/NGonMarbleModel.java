package org.rxtudelft.marbleui.diagram;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

/**
 * Created by ferdy on 6/24/14.
 */
public class NGonMarbleModel extends SimpleMarbleModel {

    private IntegerProperty num;
    private ObjectProperty<Color> color;

    public NGonMarbleModel(int num, Color color) {
        super();
        this.color = new SimpleObjectProperty<>(color);
        this.num = new SimpleIntegerProperty(num);
    }

    public int getNum() {
        return num.get();
    }

    public IntegerProperty numProperty() {
        return num;
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NGonMarbleModel that = (NGonMarbleModel) o;

        if (color != null ? !color.get().equals(that.color.get()) : that.color != null) return false;
        if (num != null ? num.get() != that.num.get() : that.num != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = num != null ? Integer.hashCode(num.get()) : 0;
        result = 31 * result + (color != null ? color.get().hashCode() : 0);
        return result;
    }
}
