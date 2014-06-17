package org.rxtudelft.marbleui.node;

import javafx.beans.property.MapProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.observables.JavaFxObservable;

import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.TreeMap;

import static java.lang.Math.round;

/**
 * A javafx.scene.Node (really a Group) that represents a whole observable, including marbles.
 */
public class NodeObservable extends Group {

    //where my ghost should be
    private ObjectProperty<OptionalDouble> ghost;

    //node object for my ghost
    private NodeGhostMarble ghostMarble;

    //all the models that I need to draw a marble for
    private MapProperty<Long, MarbleModel> marbles;

    //list of all marble nodes drawn
    private List<NodeSimpleMarble> nodeMarbles;

    //TODO should be property
    private double width;

    //TODO should be property
    private double height;

    //TODO shouldn't be hardcoded
    private double r;

    public NodeObservable(double width, double height) {
        this.width   = width;
        this.height  = height;
        this.r       = (height/2)*0.8;

        // not sure if this is the best solution to force the group to have this width/height but it works.
        Rectangle background = new Rectangle(0, 0, this.width, this.height);
        background.setFill(Color.TRANSPARENT);
        this.getChildren().add(background);

        //init ghost
        this.ghost = new SimpleObjectProperty<>(OptionalDouble.empty());
        this.ghostMarble = new NodeGhostMarble(5, r);
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
            NodeObservable.this.ghostMarble.setVisible(false);
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

                        //TODO simple/composite?
                        //TODO dif with old marbles
                        if (m instanceof SimpleMarbleModel) {
                            SimpleMarbleModel sm = (SimpleMarbleModel) m;
                            NodeSimpleMarble nm = new NodeSimpleMarble(sm.getNum(), r);
                            nm.setFill(sm.getColor());
                            nm.setTranslateX(limitX(this.msToX(t)));
                            nm.setTranslateY(height / 2);
                            this.nodeMarbles.add(nm);
                            this.getChildren().add(nm);
                        }
                    });
                });

        //init the line
        Line line = new Line(r, height/2, this.width - r, height/2);

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

    public ObservableMap<Long, MarbleModel> getMarbles() {
        return marbles.get();
    }

    public MapProperty<Long, MarbleModel> marblesProperty() {
        return marbles;
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
