package org.rxtudelft.marbleui.shape;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Created by ferdy on 5/22/14.
 */
public class NodeOperator extends Group {
    public NodeOperator(double w, double h, String name) {

        StackPane operator = new StackPane();

        javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(0, 0, w, h);

        rectangle.setStrokeType(StrokeType.CENTERED);
        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle.setStrokeWidth(2);
        rectangle.setFill(javafx.scene.paint.Color.TRANSPARENT);

        Text text = new Text(name);

        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextOrigin(VPos.CENTER);

        operator.getChildren().addAll(rectangle, text);

        this.getChildren().add(operator);
    }
}