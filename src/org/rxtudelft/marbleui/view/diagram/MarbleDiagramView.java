package org.rxtudelft.marbleui.view.diagram;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import org.rxtudelft.marbleui.diagram.MarbleDiagramModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.view.Counter;
import org.rxtudelft.marbleui.viewModel.GhostViewModel;
import org.rxtudelft.marbleui.viewModel.InObservableViewModel;
import org.rxtudelft.marbleui.viewModel.OutObservableViewModel;

import java.util.*;

/**
 * A Marble Diagram
 */
public class MarbleDiagramView extends Group {

    private MarbleDiagramModel diagramModel;

    public MarbleDiagramView(MarbleDiagramModel diagramModel) {
        this.diagramModel = diagramModel;
        double width   = 1000;
        double height  = 800;
        double h       = height / 5;

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        //ghost angle counter
        Counter angleCounter = new Counter(5);
        root.getChildren().add(angleCounter);

        //setup input nodes
        List<ObservableView> inputNodes = new ArrayList<>();
        this.diagramModel.getInputs().stream().forEach(i -> {
            //create node
            ObservableView inObs = new ObservableView(width, h);
            inputNodes.add(inObs);
            root.getChildren().add(inObs);

            //create view model
            InObservableViewModel vm = new InObservableViewModel(inObs, i);

            //add ghost vm
            new GhostViewModel(inObs.getGhostMarble(), angleCounter.iProperty());
        });

        final OperatorView nOp = new OperatorView(width, h, "Test");
        root.getChildren().add(nOp);

        //setup output node
        final ObservableView nObsOut = new ObservableView(width, h);
        root.getChildren().addAll(nObsOut);
        ObservableModel outputModel = diagramModel.getOutput();
        //attach output node to it's model
        OutObservableViewModel outVM = new OutObservableViewModel(nObsOut, outputModel);
        
        root.setPadding(new Insets(h / 2));
        this.getChildren().add(root);
    }
}
