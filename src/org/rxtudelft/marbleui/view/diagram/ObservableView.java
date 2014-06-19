package org.rxtudelft.marbleui.view.diagram;

import javafx.beans.property.MapProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import org.rxtudelft.marbleui.diagram.*;
import rx.observables.JavaFxObservable;

import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.TreeMap;

import static java.lang.Math.round;

/**
 * A javafx.scene.Node (really a Group) that represents a whole observable, including marbles.
 */
public class ObservableView<T extends MarbleModel> extends Group {

    //where my ghost should be
    private ObjectProperty<OptionalDouble> ghost;

    //node object for my ghost
    private GhostMarbleView ghostMarble;

    //all the models that I need to draw a marble for
    private MapProperty<Long, T> marbles;

    //list of all marble nodes drawn
    private List<Node> nodeMarbles;

    //TODO should be property
    private double width;

    //TODO should be property
    private double height;

    //TODO shouldn't be hardcoded
    private double r;

    public ObservableView (double width, double height) {
        this.width   = width;
        this.height  = height;
        this.r       = (height/2)*0.8;

        //init the line
        Line line = new Line(r, height/2, this.width - r, height/2);

        line.setStrokeType(StrokeType.CENTERED);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
        this.getChildren().add(line);

        // not sure if this is the best solution to force the group to have this width/height but it works.
        Rectangle background = new Rectangle(0, 0, this.width, this.height);
        background.setFill(Color.TRANSPARENT);
        this.getChildren().add(background);

        //init ghost
        this.ghost = new SimpleObjectProperty<>(OptionalDouble.empty());
        this.ghostMarble = new GhostMarbleView(5, r);
        this.ghostMarble.setTranslateY(height/2);
        this.getChildren().add(this.ghostMarble);

        //update ghost marble's position based on ghost property
        JavaFxObservable.fromObservableValue(this.ghost)
                .subscribe(mGhost -> {
                    if (mGhost.isPresent()) {
                        this.ghostMarble.setTranslateX(limitX(mGhost.getAsDouble()));
                        this.ghostMarble.setVisible(true);
                    } else {
                        this.ghostMarble.setVisible(false);
                    }
                });

        JavaFxObservable.fromNodeEvents(this, MouseEvent.MOUSE_EXITED).subscribe(s -> {
            ObservableView.this.ghostMarble.setVisible(false);
        });

        //init marbles
        this.marbles = new SimpleMapProperty<>(FXCollections.observableMap(new TreeMap<>()));
        this.nodeMarbles = new LinkedList<>();
        //TODO don't need to redo every time
        JavaFxObservable.fromObservableValue(this.marbles)
                .subscribe(ms -> {
                    //cleanup old marble nodes
                    this.nodeMarbles.forEach(this.getChildren()::remove);

                    ms.forEach((t, m) -> {
                        Node n = null;
                        if (m instanceof SimpleCompletedModel) {
                            SimpleCompletedModel sm = (SimpleCompletedModel) m;
                            n = new SimpleCompletedView(r);
                        }
                        if (m instanceof SimpleErrorModel) {
                            SimpleErrorModel sm = (SimpleErrorModel) m;
                            n = new SimpleErrorView(r);
                        }
                        if (m instanceof SimpleMarbleModel) {
                            SimpleMarbleModel sm = (SimpleMarbleModel) m;
                            SimpleMarbleView nm = new SimpleMarbleView(sm.getNum(), r);
                            nm.setFill(sm.getColor());
                            n = nm;
                        }
                        assert n != null;
                        n.setTranslateX(limitX(this.msToX(t)));
                        n.setTranslateY(height / 2);
                        this.nodeMarbles.add(n);
                        this.getChildren().add(n);
                    });

                    //put ghost on top
                    this.getChildren().remove(this.ghostMarble);
                    this.getChildren().add(this.ghostMarble);
                });
    }

    public OptionalDouble getGhost() {
        return ghost.get();
    }

    public ObjectProperty<OptionalDouble> ghostProperty() {
        return ghost;
    }

    public ObservableMap<Long, T> getMarbles() {
        return marbles.get();
    }

    public MapProperty<Long, T> marblesProperty() {
        return marbles;
    }

    public GhostMarbleView getGhostMarble() {
        return ghostMarble;
    }

    public void setGhostMarble(GhostMarbleView ghostMarble) {
        this.ghostMarble = ghostMarble;
    }

    public double limitX(double x) {
        if(x < r) {
            return r;
        }
        else if (x > width - r) {
            return width - r;
        }
        else {
            return x;
        }
    }

    public double msToX(long ms) {
        return (ms * this.width) / ObservableModel.MAX_TIME;
    }

    public long xToMs(double x) {
        return round((x / this.width) * ObservableModel.MAX_TIME);
    }
}
