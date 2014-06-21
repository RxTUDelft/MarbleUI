package org.rxtudelft.marbleui.diagram;

import javafx.scene.Node;
import org.rxtudelft.marbleui.view.diagram.ObservableView;
import org.rxtudelft.marbleui.view.diagram.SimpleMarbleView;

/**
 * Created by ferdy on 6/21/14.
 */
public class SimpleObservableView extends ObservableView {


    public SimpleObservableView(double width, double height) {
        super(width, height);
    }

    @Override
    protected Node getMarbleView(Long t, MarbleModel m) {
        Node n;
        SimpleMarbleModel sm = (SimpleMarbleModel) m;
        SimpleMarbleView nm = new SimpleMarbleView(((SimpleMarbleModel) m).getNum(), getR());
        nm.getP().setFill(sm.getColor());
        n = nm;
        return n;
    }
}
