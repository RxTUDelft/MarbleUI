package org.rxtudelft.marbleui.view.diagram.marble;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import org.rxtudelft.marbleui.diagram.StringMarbleModel;

/**
 * Created by ferdy on 6/25/14.
 */
public class StringInputMarbleView extends StringMarbleView {

    private TextArea input;
    public StringInputMarbleView(StringMarbleModel m, double w, double h) {
        super(m, w, h);
        this.input = new TextArea(m.getValue());
    }

    @Override
    public Node getNode() {
        return this.input;
    }
}
