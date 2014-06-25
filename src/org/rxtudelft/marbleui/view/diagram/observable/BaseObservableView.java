package org.rxtudelft.marbleui.view.diagram.observable;

import javafx.beans.property.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import org.rxtudelft.marbleui.diagram.CompletedModel;
import org.rxtudelft.marbleui.diagram.ErrorModel;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.view.diagram.marble.CompletedView;
import org.rxtudelft.marbleui.view.diagram.marble.ErrorView;
import org.rxtudelft.marbleui.view.diagram.marble.MarbleView;

/**
 * Created by ferdy on 6/25/14.
 */
public abstract class BaseObservableView<T extends MarbleModel> implements ObservableView<T> {
    private ObservableModel model;
    private LongProperty ghost;
    private Group root;
    private Line line;
    private BooleanProperty selected;
    private double radius;

    private double width;
    private double height;

    public BaseObservableView(ObservableModel model, double w, double h, double r) {
        this.model = model;
        this.width = w;
        this.height = h;
        this.radius = r;
        this.root = new Group();
        this.ghost = new SimpleLongProperty(0);
        this.line = obsLine();
        this.selected = new SimpleBooleanProperty(false);

        Rectangle bg = new Rectangle(w, h);

        bg.setFill(Color.TRANSPARENT);
        this.root.prefWidth(w);
        this.root.prefHeight(h);

        this.root.getChildren().add(bg);
        this.root.getChildren().add(line);
    }

    protected Line obsLine() {
        return new Line(getRadius(), getHeight()/2, getWidth() - getRadius(), getHeight()/2);
    }

    @Override
    public ObservableModel getModel() {
        return model;
    }

    @Override
    public Node getNode() {
        return this.root;
    }

    public Line getLine() {
        return  this.line;
    }

    @Override
    public void placeMarble(long at, MarbleModel m) {

        MarbleView marbleView;
        if(m instanceof ErrorModel) {
            marbleView = new ErrorView(getRadius(), getRadius());
        }
        else if(m instanceof CompletedModel) {
            marbleView = new CompletedView(getRadius(), getRadius());
        }
        else {
            marbleView = this.getMarbleView(m, at, getRadius(), getRadius());
        }

        Node n = marbleView.getNode();
        this.root.getChildren().add(n);
        n.setTranslateX(at);
        n.setTranslateY((getHeight()/2));
    }

    public void rotate(double degrees) {
        this.root.getTransforms().add(new Rotate(degrees, 0, getHeight() / 2));
    }

    @Override
    public BooleanProperty selectedProperty() {
        return this.selected;
    }

    @Override
    public LongProperty ghostProperty() {
        return this.ghostProperty();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getRadius() {
        return radius;
    }

    public Group getRoot() {
        return root;
    }
}
