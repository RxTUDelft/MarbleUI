package org.rxtudelft.marbleui.view.diagram.marble;

import javafx.scene.Group;
import org.rxtudelft.marbleui.diagram.MarbleModel;

/**
 * Created by ferdy on 6/22/14.
 */
public abstract class MarbleView extends Group {
    private MarbleModel m;

    public abstract MarbleView turnGhost();

    public MarbleView(MarbleModel m) {
        this.m = m;
    }

    public MarbleModel getModel() {
        return m;
    }

    public abstract MarbleView clone();
}
