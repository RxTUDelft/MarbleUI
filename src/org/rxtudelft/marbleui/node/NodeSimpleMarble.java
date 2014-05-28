package org.rxtudelft.marbleui.node;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.rxtudelft.marbleui.diagram.MarbleModel;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Jeff's awesome NGon used as a simple marble
 */
public class NodeSimpleMarble extends Polygon {

    public NodeSimpleMarble(int n, double r) {
        super();

        if (n <= 2) throw new IllegalArgumentException("n should be larger than 2");
        double t = 2 * PI / n;

        ObservableList<Double> points = this.getPoints();

        Stream.iterate(0, (m) -> m + 1).limit(n)
                .flatMapToDouble((i) -> DoubleStream.of(r * sin(t * i), r * cos(t * i)))
                .forEach(points::add);

        this.setFill(Color.RED);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(2);
    }
}
