package org.rxtudelft.marbleui.view.diagram;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.*;
import org.rxtudelft.marbleui.diagram.bootstrapOperator.NGonMarbleModel;
import org.rxtudelft.marbleui.view.ModePicker;
import org.rxtudelft.marbleui.view.diagram.marble.CompletedView;
import org.rxtudelft.marbleui.view.diagram.marble.ErrorView;
import org.rxtudelft.marbleui.view.diagram.marble.MarbleView;
import org.rxtudelft.marbleui.view.diagram.marble.NGonMarbleView;
import org.rxtudelft.marbleui.view.diagram.observable.NGonObservableView;
import org.rxtudelft.marbleui.view.diagram.observable.ObservableView;
import org.rxtudelft.marbleui.view.viewModel.InputObservableViewModel;
import rx.Observable;
import rx.observables.JavaFxObservable;

/**
 * Created by ferdy on 6/25/14.
 */
public class MarbleDiagramView implements View {
    private MarbleDiagramModel model;
    private VBox root;
    private double width;
    private double height;
    public static IntegerProperty corners;
    public static ObjectProperty<Color> color;
    private NGonMarbleModel ngonGhost;
    public static ObjectProperty<MarbleModel> mode;

    public MarbleDiagramView(MarbleDiagramModel model, double w, double h) {
        this.model = model;
        this.root = new VBox();
        this.width = w;
        this.height = h;

        this.ngonGhost = new NGonMarbleModel(5, Color.RED);

        this.root.prefWidth(w);
        this.root.prefHeight(h);


        //setup controls
        NGonMarbleView smallMarbleGhost = new NGonMarbleView(ngonGhost, 25, 25);
        ModePicker modePicker = new ModePicker(smallMarbleGhost, new CompletedView(25, 25), new ErrorView(25, 25));
        this.color = modePicker.colorProperty();
        this.corners = modePicker.cornersProperty();
        this.mode = modePicker.modeProperty();

        ngonGhost.numProperty().bindBidirectional(corners);
        ngonGhost.colorProperty().bindBidirectional(color);
        root.getChildren().add(modePicker);

        model.getInputs().forEach(i -> {
            ObservableView obsV = this.getObservableView(i);

            MarbleDiagramView.this.root.getChildren().add(obsV.getNode());
            new InputObservableViewModel(obsV);
        });

        ObservableView outObsV = this.getObservableView(model.getOutput());
        new InputObservableViewModel(outObsV);
        this.root.getChildren().add(outObsV.getNode());

    }

    private ObservableView getObservableView(ObservableModel obsModel) {
        if(obsModel instanceof TimestampedObservableModel) {
            return null;
        }
        else if(obsModel instanceof ComplexObservableModel) {
            return null;
        }
        else {
            return new NGonObservableView(obsModel, getWidth(), getHeight()/5, 40);
        }
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public Object getModel() {
        return this.model;
    }

    @Override
    public VBox getNode() {
        return this.root;
    }
}
