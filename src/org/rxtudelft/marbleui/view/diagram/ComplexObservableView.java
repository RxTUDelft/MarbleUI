package org.rxtudelft.marbleui.view.diagram;

import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.GroupedObservableModel;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;

/**
 * Created by ferdy on 6/21/14.
 */
public class ComplexObservableView extends ObservableView {
    public ComplexObservableView(double width, double height) {
        super(width, height);
    }

    protected Node getMarbleView(Long t, MarbleModel m) {
        Node n;
        GroupedObservableModel gm = (GroupedObservableModel) m;
        ((GroupedObservableModel) m).getMarbles().forEach((k, v) -> {
            System.out.println("[" + gm.getKey() + "]" + k + ": " + v);
        });
        MarbleView nm = new SimpleMarbleView(3, 20);
        nm.getP().setFill(Color.GOLD);
        n = nm;
        return n;
    }
}
