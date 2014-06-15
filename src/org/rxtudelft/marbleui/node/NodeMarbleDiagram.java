package org.rxtudelft.marbleui.node;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.MarbleDiagramModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import org.rxtudelft.marbleui.viewModel.ObservableViewModel;
import rx.observables.JavaFxObservable;

import java.util.*;

/**
 * A Marble Diagram
 */
public class NodeMarbleDiagram extends Group {

    private MarbleDiagramModel diagramModel;

    public NodeMarbleDiagram(MarbleDiagramModel diagramModel) {
        this.diagramModel = diagramModel;
        double width   = 1000;
        double height  = 800;
        double h       = height / 5;

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        //setup input nodes
        List<NodeObservable> inputNodes = new ArrayList<>();
        this.diagramModel.getInputs().stream().forEach(i -> {
            //create node
            NodeObservable nObs = new NodeObservable(width, h);
            inputNodes.add(nObs);
            root.getChildren().add(nObs);

            //create model
            ObservableModel m = new ObservableModel();

            //create view model
            ObservableViewModel vm = new ObservableViewModel(nObs, m, true);
        });

        final NodeOperator nOp = new NodeOperator(width, h, "Test");
        root.getChildren().add(nOp);

        final NodeObservable nObsOut = new NodeObservable(width, h);
        root.getChildren().addAll(nObsOut);

        root.setPadding(new Insets(h / 2));
        this.getChildren().add(root);
    }
}
