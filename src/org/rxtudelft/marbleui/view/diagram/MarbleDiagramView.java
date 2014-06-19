package org.rxtudelft.marbleui.view.diagram;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.rxtudelft.marbleui.diagram.MarbleDiagramModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.TimestampedObservableModel;
import org.rxtudelft.marbleui.view.ColorPicker;
import org.rxtudelft.marbleui.view.Counter;
import org.rxtudelft.marbleui.viewModel.GhostViewModel;
import org.rxtudelft.marbleui.viewModel.InObservableViewModel;
import org.rxtudelft.marbleui.viewModel.OutObservableViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A Marble Diagram
 */
public class MarbleDiagramView extends Group {

    private MarbleDiagramModel diagramModel;

    public MarbleDiagramView(MarbleDiagramModel diagramModel) {
        this.diagramModel = diagramModel;
        double width   = 1000;
        double height  = 800;
        double h       = height / 6;

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        //setup controls
        HBox controls = new HBox();

        ColorPicker colorPicker = new ColorPicker(150, 45);
        controls.getChildren().add(colorPicker);

        Counter angleCounter = new Counter(5);
        controls.getChildren().add(angleCounter);



        root.getChildren().add(controls);

        //setup input nodes
        List<ObservableView> inputNodes = new ArrayList<>();
        this.diagramModel.getInputs().stream().forEach(i -> {
            //create node
            ObservableView inObs = new ObservableView(width, h);
            inputNodes.add(inObs);
            root.getChildren().add(inObs);

            //create view model
            InObservableViewModel vm = new InObservableViewModel(inObs, (org.rxtudelft.marbleui.diagram.InObservableModel) i);

            //add ghost vm
            new GhostViewModel(inObs.getGhostMarble(), angleCounter.iProperty(), colorPicker.getColor());
        });

        final OperatorView nOp = new OperatorView(width, h, "Test");
        root.getChildren().add(nOp);

        //setup output node
        final ObservableView nObsOut = this.getOutObservableModel(diagramModel.getOutput(), width, h);
        root.getChildren().addAll(nObsOut);
        ObservableModel outputModel = diagramModel.getOutput();
        //attach output node to it's model
        OutObservableViewModel outVM = new OutObservableViewModel(nObsOut, outputModel);
        
        root.setPadding(new Insets(h / 2));
        this.getChildren().add(root);
    }

    public ObservableView getOutObservableModel(ObservableModel<?> obsOutmodel, double width, double height) {
        if(obsOutmodel instanceof TimestampedObservableModel) {
            return new TimestampedObservableView(width, height);
        }

        else {
            return new ObservableView(width, height);
        }
    }
}
