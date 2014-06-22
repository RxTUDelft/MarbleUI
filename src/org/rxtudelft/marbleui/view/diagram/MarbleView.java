package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.Group;
import org.rxtudelft.marbleui.diagram.MarbleModel;

/**
 * Created by ferdy on 6/22/14.
 */
public class MarbleView extends Group {
    private MarbleModel m;

    public MarbleView(MarbleModel m) {
        this.m = m;
    }

    public MarbleModel getM() {
        return m;
    }
}
