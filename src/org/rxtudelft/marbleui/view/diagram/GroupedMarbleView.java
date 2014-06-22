package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.rxtudelft.marbleui.diagram.ChildObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;

/**
 * Created by ferdy on 6/22/14.
 */
public class GroupedMarbleView extends MarbleView {

    private int offset;

    public GroupedMarbleView(ChildObservableModel m, double r, int offset, double width, double dxdy) {
        super(m);
        this.offset = offset;
        double dx = width - offset - r;
        double dy = dx*dxdy;

        Line obsLine = new Line(0, 0, dx, dy);
        obsLine.setStrokeWidth(2);
        obsLine.setStroke(Color.BLACK);
        this.getChildren().add(obsLine);

        m.getMarbles().forEach((t, marble) -> {
            NGonMarbleView n = new NGonMarbleView((SimpleMarbleModel)marble, r);
            this.getChildren().add(n);
            double x = t - offset;
            n.setTranslateX(x);
            n.setTranslateY(x * dxdy);
        });
    }
}
