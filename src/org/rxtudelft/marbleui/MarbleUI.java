package org.rxtudelft.marbleui;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.rxtudelft.marbleui.diagram.MarbleDiagramModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import org.rxtudelft.marbleui.diagram.bootstrapOperator.BootstrapMap;
import org.rxtudelft.marbleui.view.Counter;
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

        VBox ui = new VBox();
        Counter ghostNumContol = new Counter(5);
        ui.getChildren().add(ghostNumContol);



        ObservableModel obs = new ObservableModel();
        List<ObservableModel> inputs = new ArrayList<ObservableModel>();
        inputs.add(obs);
        MarbleDiagramModel diagramModel = new MarbleDiagramModel(inputs, new BootstrapMap(o -> {
                SimpleMarbleModel s = (SimpleMarbleModel)o;
                return new SimpleMarbleModel(s.getNum()+1, s.getColor());
        }));

        ui.getChildren().add(new MarbleDiagramView(diagramModel));

        stage.setScene(new Scene(ui, width, height, Color.WHITE));

        stage.show();
    }
}
