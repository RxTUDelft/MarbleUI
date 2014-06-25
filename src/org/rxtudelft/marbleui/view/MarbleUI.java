package org.rxtudelft.marbleui.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.rxtudelft.marbleui.diagram.MarbleDiagramModel;
import org.rxtudelft.marbleui.diagram.bootstrapOperator.*;
import org.rxtudelft.marbleui.view.diagram.MarbleDiagramView;

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
        MarbleDiagramModel diagram;
        diagram = new MarbleDiagramModel(new BootstrapMap(a -> a));
//        diagram = new MarbleDiagramModel(new BootstrapByLine());
//        diagram = new MarbleDiagramModel(new BootstrapParallelMerge(3));

        stage.setScene(new Scene(new MarbleDiagramView(diagram, width, height).getNode(), width, height, Color.WHITE));

        stage.show();
    }
}