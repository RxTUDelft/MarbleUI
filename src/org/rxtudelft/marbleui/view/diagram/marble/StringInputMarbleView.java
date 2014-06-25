package org.rxtudelft.marbleui.view.diagram.marble;

import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import org.rxtudelft.marbleui.diagram.StringMarbleModel;
import org.rxtudelft.marbleui.view.DrawButton;

/**
 * Created by ferdy on 6/25/14.
 */
public class StringInputMarbleView extends StringMarbleView {

    private HBox root;
    private TextArea input;
    private StringProperty text;

    public StringInputMarbleView(StringMarbleModel m, double w, double h) {
        super(m, w, h);
        this.input = new TextArea(m.getValue());
        this.root = new HBox();
        this.root.getChildren().add(new DrawButton(w, h, new Label("Text")));
        this.root.getChildren().add(this.input);
        this.text = this.input.textProperty();
        this.text.bindBidirectional(m.valueProperty());
    }

    @Override
    public Node getNode() {
        return this.root;
    }

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }
}
