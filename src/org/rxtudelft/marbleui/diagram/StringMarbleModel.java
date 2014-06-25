package org.rxtudelft.marbleui.diagram;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by ferdy on 6/25/14.
 */
public class StringMarbleModel extends SimpleMarbleModel {
    private StringProperty value;

    public StringMarbleModel(String value) {
        this.value = new SimpleStringProperty(value);
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }
}
