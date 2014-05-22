package org.rxtudelft.marbleui.diagram;

/**
 * Created by jeff on 22-5-14.
 */
public final class GhostMarble {
    private int obs;
    private double x;

    public double getX() {
        return x;
    }

    public int getObs() {
        return obs;
    }

    public GhostMarble(int obs, double x) {
        this.obs = obs;
        this.x = x;
    }
}
