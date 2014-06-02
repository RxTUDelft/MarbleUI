package org.rxtudelft.marbleui.node;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import rx.observables.JavaFxObservable;

/**
 * Group to show a marble diagram operator
 */
public class NodeOperator extends Group {

    //name to display in the center
    private String name;

    private Rectangle box;
    public NodeOperator(double w, double h, String name) {

        this.name = name;

        StackPane operator = new StackPane();

        this.box = new javafx.scene.shape.Rectangle(0, 0, w, h);

        box.setStrokeType(StrokeType.CENTERED);
        box.setStroke(javafx.scene.paint.Color.BLACK);
        box.setStrokeWidth(2);
        box.setFill(javafx.scene.paint.Color.TRANSPARENT);

        Text text = new Text(name);

        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextOrigin(VPos.CENTER);

        operator.getChildren().addAll(box, text);

        this.getChildren().add(operator);
        this.autoSizeChildrenProperty().set(true);
    }
}