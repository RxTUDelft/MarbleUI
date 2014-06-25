package org.rxtudelft.marbleui;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.rxtudelft.marbleui.diagram.MarbleDiagramModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import org.rxtudelft.marbleui.diagram.bootstrapOperator.*;
import org.rxtudelft.marbleui.view.diagram.MarbleDiagramView;

import java.util.ArrayList;
import java.util.List;

/**
 *
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

        //setup diagram model
//        MarbleDiagramModel diagramModel = new MarbleDiagramModel(new BootstrapDistinct());
        MarbleDiagramModel diagramModel = new MarbleDiagramModel(new BootstrapParallelMerge(2));

        stage.setScene(new Scene(new MarbleDiagramView(diagramModel), width, height, Color.WHITE));

        stage.show();
    }
}
