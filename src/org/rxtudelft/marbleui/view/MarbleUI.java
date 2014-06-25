package org.rxtudelft.marbleui.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.rxtudelft.marbleui.diagram.MarbleDiagramModel;
import org.rxtudelft.marbleui.diagram.NGonMarbleModel;
import org.rxtudelft.marbleui.diagram.bootstrapOperator.*;
import org.rxtudelft.marbleui.view.diagram.MarbleDiagramView;
import rx.observables.JavaFxObservable;

import javax.swing.*;

/**
 * Created by ferdy on 6/25/14.
 */
public class MarbleUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static final double width  = 800;
    public static final double height = 600;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MarbleUI");

        VBox ui = new VBox();

        ComboBox<BootstrapOperator> diagrams = new ComboBox<>();
        ui.getChildren().add(diagrams);
        ui.getChildren().add(new MarbleDiagramView(new MarbleDiagramModel(new BootstrapDistinct()), width, height).getNode());
        diagrams.itemsProperty().get().add(new BootstrapByLine());
        diagrams.itemsProperty().get().add(new BootstrapCombineLatest((a, b) -> {
            return new NGonMarbleModel(Math.min(a.getNum(), b.getNum()), b.getColor());
        }));
        diagrams.itemsProperty().get().add(new BootstrapDistinct());
        diagrams.itemsProperty().get().add(new BootstrapDistinctUntilChanged());
        diagrams.itemsProperty().get().add(new BootstrapFilter(a -> {
            return a.getColor().getGreen() > a.getColor().getRed();
        }));
        diagrams.itemsProperty().get().add(new BootstrapGroupBy<Integer>(m -> {
            return ((NGonMarbleModel)m).getNum();
        }));
        diagrams.itemsProperty().get().add(new BootstrapMap(a -> a));
        diagrams.itemsProperty().get().add(new BootstrapSum());
        diagrams.itemsProperty().get().add(new BootstrapMax());
        diagrams.itemsProperty().get().add(new BootstrapMerge());
        diagrams.itemsProperty().get().add(new BootstrapMergeDelayError());
        diagrams.itemsProperty().get().add(new BootstrapParallelMerge(2));
        diagrams.itemsProperty().get().add(new BootstrapJoin());
        diagrams.itemsProperty().get().add(new BootstrapByLine());
        diagrams.itemsProperty().get().add(new BootstrapSplit(" "));
        diagrams.itemsProperty().get().add(new BootstrapTimestamp());
        diagrams.itemsProperty().get().add(new BootstrapWindow(3));
        diagrams.itemsProperty().get().add(new BootstrapZip((a, b) -> {
            return new NGonMarbleModel(a.getNum() + b.getNum(), b.getColor());
        }));
        diagrams.itemsProperty().get().add(new BootstrapReduce(new NGonMarbleModel(3, Color.GRAY), (a, b) -> {
            Color cA = a.getColor();
            Color cB = a.getColor();
            return new NGonMarbleModel(Math.min(a.getNum(), b.getNum()),
                    Color.color(
                            (cA.getRed()+cB.getRed())/2,
                            (cA.getGreen()+cB.getGreen())/2,
                            (cA.getBlue()+cB.getRed())/2));
        }));
        MarbleDiagramModel diagram;
        JavaFxObservable.fromObservableValue(diagrams.valueProperty()).subscribe(d -> {
            if (d != null) {
                ui.getChildren().remove(1);
                ui.getChildren().add(new MarbleDiagramView(new MarbleDiagramModel(d), width, height).getNode());
            }});

        stage.setScene(new Scene(ui, width, height, Color.WHITE));

        stage.show();
    }
}