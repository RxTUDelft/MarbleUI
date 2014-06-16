package org.rxtudelft.marbleui;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.rxtudelft.marbleui.diagram.MarbleDiagramModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.bootstrapOperator.BootstrapMap;
import org.rxtudelft.marbleui.node.NodeMarbleDiagram;

import java.util.ArrayList;
import java.util.LinkedList;
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

        ObservableModel obs = new ObservableModel();
        List<ObservableModel> inputs = new ArrayList<ObservableModel>();
        inputs.add(obs);
        MarbleDiagramModel diagramModel = new MarbleDiagramModel(inputs, new BootstrapMap(o -> o));

        stage.setScene(new Scene(new NodeMarbleDiagram(diagramModel), width, height, Color.WHITE));

        stage.show();
    }
}
