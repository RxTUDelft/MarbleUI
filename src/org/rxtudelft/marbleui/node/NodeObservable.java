package org.rxtudelft.marbleui.node;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import org.rxtudelft.marbleui.NGon;
import rx.Observer;
import rx.observables.JavaFxObservable;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A javafx.scene.Node (really a Group) that represents a whole observable, including marbles.
 */
public class NodeObservable extends Group {
    private final List<Double> marbles;
    private Double ghost;
    private double width;
    private double height;

    public NodeObservable(int id, double width, double height, List<Double>[] marbles, Double ghost, Observer<Double>[] clicks, Observer<Double>[] hovers) {
        this.ghost   = ghost;
        this.width   = width;
        this.height  = height;
        this.marbles = marbles[id];

        init();

        JavaFxObservable.fromNodeEvents(this, MouseEvent.MOUSE_CLICKED)
                .map(MouseEvent::getX)
                .map(x -> Math.min(width - height / 2, Math.max( height / 2, x)))
                .subscribe(clicks[id]);
        JavaFxObservable.fromNodeEvents(this, MouseEvent.MOUSE_MOVED)
                .map(MouseEvent::getX)
                .map(x -> Math.min(width - height / 2, Math.max(height / 2, x)))
                .subscribe(hovers[id]);
    }


    private void init() {
        double padding = height/2;

        // not sure if this is the best solution to force the group to have this width/height but it works.
        Rectangle background = new Rectangle(0, 0, this.width, this.height);
        background.setFill(Color.TRANSPARENT);

        Double m = this.marbles.stream().max(Comparator.<Double>naturalOrder()).orElseGet(() -> 0d);
        Line line = new Line(0, 0, Math.max(this.width - 2 * padding, m), 0);

        line.setStrokeType(StrokeType.CENTERED);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);

        line.setTranslateX(padding);
        line.setTranslateY(padding);

        List<Polygon> pentagons = this.marbles.stream().map((x) -> {
            Polygon pentagon = NGon.pointUp(0, padding, 5, 0.8 * padding);

            pentagon.setStrokeType(StrokeType.INSIDE);
            pentagon.setStroke(Color.BLACK);
            pentagon.setStrokeWidth(2);
            pentagon.setFill(Color.TRANSPARENT);

            pentagon.setTranslateX(x);

            return pentagon;
        }).collect(Collectors.toList());

        this.getChildren().addAll(background, line);
        this.getChildren().addAll(pentagons);

        if(this.ghost != null) {
            Polygon ghostPentagon = NGon.pointUp(0, padding, 5, 0.8 * padding);

            ghostPentagon.setStrokeType(StrokeType.INSIDE);
            ghostPentagon.setStroke(Color.GRAY);
            ghostPentagon.setStrokeWidth(2);
            ghostPentagon.setFill(Color.TRANSPARENT);

            ghostPentagon.setTranslateX(this.ghost);

            this.getChildren().add(ghostPentagon);
        }
    }
}
