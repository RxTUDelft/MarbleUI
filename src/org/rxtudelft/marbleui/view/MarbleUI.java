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
        diagrams.itemsProperty().get().add(new BootstrapMap(a -> a));

        MarbleDiagramModel diagram;
//        diagram = new MarbleDiagramModel(new BootstrapMap(a -> a));
        diagram = new MarbleDiagramModel(new BootstrapByLine());
        diagram = new MarbleDiagramModel(new BootstrapMergeDelayError());
//        diagram = new MarbleDiagramModel(new BootstrapGroupBy<Integer>(m -> {
//            return ((NGonMarbleModel)m).getNum();
//        }));

        ui.getChildren().add(new MarbleDiagramView(diagram, width, height).getNode());

        stage.setScene(new Scene(ui, width, height, Color.WHITE));

        stage.show();
    }
}