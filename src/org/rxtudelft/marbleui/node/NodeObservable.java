package org.rxtudelft.marbleui.node;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.observables.JavaFxObservable;

import java.util.*;

/**
 * A javafx.scene.Node (really a Group) that represents a whole observable, including marbles.
 */
public class NodeObservable extends Group {

    //where my ghost should be
    private ObjectProperty<OptionalDouble> ghost;

    //node object for my ghost
    private NodeGhostMarble ghostMarble;

    //all the models that I need to draw a marble for
    private ListProperty<SimpleMarbleModel> marbles;

    //list of all marble nodes drawn
    private List<NodeSimpleMarble> nodeMarbles;

    //TODO should be property
    private double width;

    //TODO should be property
    private double height;

    //TODO shouldn't be hardcoded
    public static int r = 100;

    public NodeObservable(double width, double height) {
        this.width   = width;
        this.height  = height;

        // not sure if this is the best solution to force the group to have this width/height but it works.
        Rectangle background = new Rectangle(0, 0, this.width, this.height);
        background.setFill(Color.TRANSPARENT);
        this.getChildren().add(background);

        //init ghost
        this.ghost = new SimpleObjectProperty<>(OptionalDouble.empty());
        this.ghostMarble = new NodeGhostMarble(5, r);
        this.getChildren().add(this.ghostMarble);

        //update ghost marble's position based on ghost property
        JavaFxObservable.fromObservableValue(this.ghost)
                .subscribe(mGhost -> {
                    if (mGhost.isPresent()) {
                        this.ghostMarble.setTranslateX(mGhost.getAsDouble());
                        this.ghostMarble.setVisible(true);
                    } else {
                        this.ghostMarble.setVisible(false);
                    }
                });

        //init marbles
        this.marbles = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.nodeMarbles = new LinkedList<>();
        //TODO don't need to redo every time
        JavaFxObservable.fromObservableValue(this.marbles)
                .subscribe(ms -> {
                    //cleanup old marble nodes
                    this.nodeMarbles.forEach(this.getChildren()::remove);

                    ms.forEach(m -> {
                        //TODO simple/composite?
                        //TODO dif with old marbles
                        NodeSimpleMarble nm = new NodeSimpleMarble(m.getNum(), r);
                        nm.setFill(m.getColor());
                        nm.setTranslateX(m.getT());
                        this.nodeMarbles.add(nm);
                        this.getChildren().add(nm);
                    });
                });

        //init the line
        Line line = new Line(0, 0, this.width, 0);

        line.setStrokeType(StrokeType.CENTERED);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
        this.getChildren().add(line);
    }

    public OptionalDouble getGhost() {
        return ghost.get();
    }

    public ObjectProperty<OptionalDouble> ghostProperty() {
        return ghost;
    }

    public ObservableList<SimpleMarbleModel> getMarbles() {
        return marbles.get();
    }

    public ListProperty<SimpleMarbleModel> marblesProperty() {
        return marbles;
    }
}
