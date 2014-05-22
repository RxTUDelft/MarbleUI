package org.rxtudelft.marbleui.diagram;

/**
 * Created by ferdy on 5/8/14.
 */
public class MarbleModel {
    private String name;

    public MarbleModel(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.getName();
    }
}
