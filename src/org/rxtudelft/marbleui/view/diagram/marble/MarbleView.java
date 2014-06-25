package org.rxtudelft.marbleui.view.diagram.marble;

import javafx.scene.Group;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.view.diagram.View;

/**
 * Created by ferdy on 6/22/14.
 */
public interface MarbleView extends View {
    @Override
    public MarbleModel getModel();

    public void turnGhost();
}
