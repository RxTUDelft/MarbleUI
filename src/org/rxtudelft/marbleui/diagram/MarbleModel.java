package org.rxtudelft.marbleui.diagram;

/**
 * Created by ferdy on 5/8/14.
 */
abstract public class MarbleModel {

    private double t;

    public MarbleModel(double t) {
        this.t = t;
    }

    public double getT() {
        return t;
    }
}
