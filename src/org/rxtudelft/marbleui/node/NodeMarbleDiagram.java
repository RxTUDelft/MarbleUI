package org.rxtudelft.marbleui.node;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import rx.observables.JavaFxObservable;

import java.util.OptionalDouble;

/**
 * A Marble Diagram
 */
public class NodeMarbleDiagram extends Group {

    public NodeMarbleDiagram() {
        double width   = 1000;
        double height  = 800;
        double h       = height / 5;

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        final NodeObservable nObs;
        nObs = new NodeObservable(width, h);
        root.getChildren().add(nObs);

        JavaFxObservable.fromNodeEvents(nObs, MouseEvent.MOUSE_MOVED)
                .subscribe(e -> {
            nObs.ghostProperty().set(OptionalDouble.of(e.getX()));
        });

        JavaFxObservable.fromNodeEvents(nObs, MouseEvent.MOUSE_CLICKED)
                .subscribe(e -> {
            nObs.marblesProperty().add(new MarbleModel(e.getX()));
        });

        final NodeOperator nOp = new NodeOperator(width, h, "Test");
        root.getChildren().add(nOp);

        final NodeObservable nObsOut = new NodeObservable(width, h);
        root.getChildren().addAll(nObsOut);

        root.setPadding(new Insets(h / 2));
        this.getChildren().add(root);
    }
}
