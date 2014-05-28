package org.rxtudelft.marbleui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Group;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.node.NodeObservable;
import org.rxtudelft.marbleui.node.NodeOperator;

import rx.observables.JavaFxObservable;
import java.util.OptionalDouble;

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

        stage.setScene(new Scene(new Group(), width, height, Color.WHITE));

        double width   = 1000;
        double height  = 800;
        double h       = height / 5;

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        final NodeObservable nObs;
        nObs = new NodeObservable(width, h);
        root.getChildren().add(nObs);

        JavaFxObservable.fromNodeEvents(nObs, MouseEvent.MOUSE_MOVED)
                .subscribe(e -> {
                    nObs.ghostProperty().set(OptionalDouble.of(e.getX()));
                });

        JavaFxObservable.fromNodeEvents(nObs, MouseEvent.MOUSE_CLICKED)
                .subscribe(e -> {
                    nObs.marblesProperty().add(new MarbleModel(e.getX()));
                });

        final NodeOperator nOp = new NodeOperator(width, h, "Test");
        root.getChildren().add(nOp);

        final NodeObservable nObsOut = new NodeObservable(width, h);
        root.getChildren().addAll(nObsOut);

        root.setPadding(new Insets(h / 2));

        stage.getScene().setRoot(root);
        stage.show();
    }
}
