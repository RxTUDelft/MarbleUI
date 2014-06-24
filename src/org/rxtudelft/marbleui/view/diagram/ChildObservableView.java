package org.rxtudelft.marbleui.view.diagram;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.rxtudelft.marbleui.diagram.*;
import org.rxtudelft.marbleui.diagram.bootstrapOperator.NGonMarbleModel;

/**
 * Created by ferdy on 6/22/14.
 */
public class ChildObservableView extends MarbleView {

    private Line obsLine;
    private double r;
    private int offset;
    private double width;
    private double dxyx;

    public ChildObservableView(ChildObservableModel m, double r, int offset, double width, double dxdy) {
        super(m);
        this.r = r;
        this.offset = offset;
        this.width = width;
        this.dxyx = dxdy;

        double dx = width - offset - r;
        double dy = dx*dxdy;

        this.obsLine = new Line(0, 0, dx, dy);
        this.obsLine.setStrokeWidth(2);
        this.obsLine.setStroke(Color.BLACK);
        this.getChildren().add(this.obsLine);

        m.getMarbles().forEach((t, marble) -> {

            Node n;
            if (marble instanceof ErrorModel) {
                n = new ErrorView(r);
            }
            else if (marble instanceof CompletedModel) {
                n = new CompletedView(r);
            }
            else {
                n = new NGonMarbleView((NGonMarbleModel)marble, r);
            }

            this.getChildren().add(n);
            double x = t - offset;
            n.setTranslateX(x);
            n.setTranslateY(x * dxdy);
        });
    }

    @Override
    public ChildObservableView turnGhost() {
        this.obsLine.setStroke(Color.GRAY);

        return this;
    }

    @Override
    public ChildObservableModel getModel() {
        return (ChildObservableModel) super.getModel();
    }

    @Override
    public MarbleView clone() {
        return new ChildObservableView(this.getModel(), this.r, this.offset, this.width, this.dxyx);
    }
}
