package org.rxtudelft.marbleui.view.diagram;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.MarbleDiagramModel;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.view.ColorPicker;
import org.rxtudelft.marbleui.view.Counter;
import org.rxtudelft.marbleui.view.ModePicker;
import org.rxtudelft.marbleui.viewModel.ObservableViewModel;
import org.rxtudelft.marbleui.viewModel.OutObservableViewModel;
import rx.Observable;
import rx.observables.JavaFxObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * A Marble Diagram
 */
public class MarbleDiagramView<T extends MarbleModel> extends Group {

    private MarbleDiagramModel diagramModel;

    public MarbleDiagramView(MarbleDiagramModel diagramModel) {
        this.diagramModel = diagramModel;
        double width   = 1000;
        double height  = 800;
        double h       = height / 5;

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        //setup controls
        HBox controls = new HBox();

        ColorPicker colorPicker = new ColorPicker(150, 45);
        controls.getChildren().add(colorPicker);

        Counter sidesCounter = new Counter(5);
        controls.getChildren().add(sidesCounter);
        Observable<Integer> sides = JavaFxObservable.fromObservableValue(sidesCounter.iProperty()).map(Number::intValue);

        SimpleMarbleView smallMarbleGhost = new SimpleMarbleView(5, 25);
        ModePicker modePicker = new ModePicker(smallMarbleGhost, new SimpleCompletedView(25), new SimpleErrorView(25));
        sides.subscribe(newN -> smallMarbleGhost.nProperty().setValue(newN));
        controls.getChildren().add(modePicker);
        Observable<MarbleView> mode = JavaFxObservable.fromObservableValue(modePicker.ghostProperty());

        root.getChildren().add(controls);

        //setup input nodes
        List<ObservableView> inputNodes = new ArrayList<>();
        this.diagramModel.getInputs().stream().forEach(i -> {
            //create node
            ObservableView inObs = new ObservableView(width, h);
            mode.subscribe(inObs::setGhostMarble);
            inputNodes.add(inObs);
            root.getChildren().add(inObs);

            //create view model
            ObservableViewModel vm = new ObservableViewModel(inObs, i);

            //add ghost vm
            ghostViewModel(inObs, sides, colorPicker.getColor());
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

    private void ghostViewModel(ObservableView observableView, Observable<Integer> sides, Observable<Color> color) {
        sides.subscribe(newN -> observableView.nProperty().setValue(newN));
        color.subscribe(newColor -> observableView.colorProperty().setValue(newColor));
    }
}
