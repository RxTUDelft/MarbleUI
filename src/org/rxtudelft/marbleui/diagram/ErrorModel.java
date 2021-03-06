package org.rxtudelft.marbleui.diagram;

/**
 * Created by jeff on 19-6-14.
 */
public class ErrorModel extends SimpleMarbleModel {
    private final Throwable e;

    public ErrorModel(Throwable e) {
        this.e = e;
    }

    public ErrorModel() {
        e = null;
    }

    public Throwable getE() {
        return e;
    }
}
