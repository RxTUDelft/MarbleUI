package org.rxtudelft.marbleui;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.rxtudelft.marbleui.diagram.*;
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
        List<InObservableModel> inputs = new ArrayList<InObservableModel>();
        InObservableModel obs1 = new InObservableModel();
        inputs.add(obs1);
//        InObservableModel obs2 = new InObservableModel();
//        inputs.add(obs2);
        MarbleDiagramModel diagramModel = new MarbleDiagramModel(inputs, new BootstrapTimestamp());

        stage.setScene(new Scene(new MarbleDiagramView(diagramModel), width, height, Color.WHITE));

        stage.show();
    }
}
