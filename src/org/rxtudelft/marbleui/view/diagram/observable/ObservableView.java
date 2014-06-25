package org.rxtudelft.marbleui.view.diagram.observable;

import javafx.beans.property.*;
import javafx.scene.Node;
import org.rxtudelft.marbleui.diagram.*;
import org.rxtudelft.marbleui.view.diagram.View;
import org.rxtudelft.marbleui.view.diagram.marble.MarbleView;

import static java.lang.Math.round;

/**
 * A javafx.scene.Node (really a Group) that represents a whole observable, including marbles.
 */
public interface ObservableView<T extends MarbleModel> extends View {
    @Override
    public ObservableModel getModel();

    public Node getNode();

    public MarbleView getMarbleView(MarbleModel m, double t, double w, double h);

    //Allow a marble to place itself at a certain time
    public void placeMarble(long at, MarbleModel m);

    //is this obs selected to edit
    public BooleanProperty selectedProperty();

    //where the ghost should be
    public LongProperty ghostProperty();

    public void clear();
}
