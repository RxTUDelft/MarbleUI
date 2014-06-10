package org.rxtudelft.marbleui.node;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import org.rxtudelft.marbleui.viewModel.ObservableViewModel;
import rx.observables.JavaFxObservable;

import java.util.HashMap;
import java.util.LinkedList;
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

        final NodeOperator nOp = new NodeOperator(width, h, "Test");
        root.getChildren().add(nOp);

        final NodeObservable nObsOut = new NodeObservable(width, h);
        root.getChildren().addAll(nObsOut);

        ObservableModel obs1M = new ObservableModel(new HashMap<>());

        ObservableViewModel vm = new ObservableViewModel(nObs, obs1M, true);

        root.setPadding(new Insets(h / 2));
        this.getChildren().add(root);
    }
}
