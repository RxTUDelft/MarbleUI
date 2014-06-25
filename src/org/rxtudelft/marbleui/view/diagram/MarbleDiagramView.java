package org.rxtudelft.marbleui.view.diagram;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.*;
import org.rxtudelft.marbleui.diagram.NGonMarbleModel;
import org.rxtudelft.marbleui.view.ModePicker;
import org.rxtudelft.marbleui.view.diagram.hybrid.ChildObservableView;
import org.rxtudelft.marbleui.view.diagram.marble.*;
import org.rxtudelft.marbleui.view.diagram.observable.NGonObservableView;
import org.rxtudelft.marbleui.view.diagram.observable.ObservableView;
import org.rxtudelft.marbleui.view.diagram.observable.StringObservableView;
import org.rxtudelft.marbleui.view.diagram.observable.TimestampedObservableView;
import org.rxtudelft.marbleui.view.viewModel.InputObservableViewModel;
import org.rxtudelft.marbleui.view.viewModel.OutputObservableViewModel;
import rx.Observable;

/**
 * Created by ferdy on 6/25/14.
 */
public class MarbleDiagramView implements View {
    public static MarbleDiagramModel model;
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
        ModePicker modePicker = new ModePicker(
                smallMarbleGhost,
                new CompletedView(25, 25),
                new ErrorView(25, 25),
                new ChildObservableView(new ChildObservableModel(), 50, 50, 0, 30, 0),
                new StringInputMarbleView(new StringMarbleModel("input"), 100, 100));
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

        this.root.getChildren().add(new OperatorView(model.getOperator().getLabel(), w, h/5));

        ObservableView outObsV = this.getObservableView(model.getOutput());
        new OutputObservableViewModel(outObsV);
        this.root.getChildren().add(outObsV.getNode());
    }

    private ObservableView getObservableView(ObservableModel obsModel) {
        if(obsModel instanceof TimestampedObservableModel) {
            return new TimestampedObservableView(obsModel, getWidth(), getHeight()/5, 40);
        }
        else if(obsModel instanceof ComplexObservableModel) {
            return new ComplexObservableView(obsModel, getWidth(), getHeight()/5, 40);
        }
        else if(obsModel instanceof StringObservableModel) {
            return new StringObservableView(obsModel, getWidth(), getHeight()/5, 40);
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
