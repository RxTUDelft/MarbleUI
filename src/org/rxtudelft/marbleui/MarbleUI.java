package org.rxtudelft.marbleui;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.rxtudelft.marbleui.node.NodeMarbleDiagram;

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

        stage.setScene(new Scene(new NodeMarbleDiagram(), width, height, Color.WHITE));

        stage.show();
    }
}
