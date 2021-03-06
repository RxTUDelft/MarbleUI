package org.rxtudelft.marbleui.view.diagram.marble;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import org.rxtudelft.marbleui.diagram.StringMarbleModel;

import java.util.Random;

/**
 * Created by ferdy on 6/25/14.
 */
public class StringMarbleView extends SimpleMarbleView {
    private Label label;

    public StringMarbleView(StringMarbleModel m, double w, double h) {
        super(m, w, h);

        this.label = new Label(m.getValue());
        Random r = new Random();
        this.label.setTranslateY(r.ints(50).findFirst().getAsInt());
    }
    
    @Override
    public void turnGhost() {
        this.label.setTextFill(Color.GRAY);
    }

    @Override
    public Node getNode() {
        return label;
    }
}
