package org.rxtudelft.marbleui.diagram;

/**
 * Created by ferdy on 6/25/14.
 */
public class StringMarbleModel extends SimpleMarbleModel {
    private String value;

    public StringMarbleModel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
