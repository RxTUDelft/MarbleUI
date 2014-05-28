package org.rxtudelft.marbleui.node;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.collections.ObservableList;
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
public class NodeSimpleMarble extends NodeMarble {
    public MarbleModel model;

    public MarbleModel getModel() {
        return this.model;
    }

    public void setModel(MarbleModel model) {
        this.model = model;
    }

    public NodeSimpleMarble(MarbleModel model, double x, double y, int n, double r) {
        super();

        this.model = model;
        Polygon polygon = new Polygon();
        if (n <= 2) throw new IllegalArgumentException("n should be larger than 2");
        double t = 2 * PI / n;

        ObservableList<Double> points = polygon.getPoints();

        Stream.iterate(0, (m) -> m + 1).limit(n)
                .flatMapToDouble((i) -> DoubleStream.of(x + r * sin(t * i), y - r * cos(t * i)))
                .forEach(points::add);
    }

    @Override
    protected NGNode impl_createPeer() {
        return null;
    }

    @Override
    public BaseBounds impl_computeGeomBounds(BaseBounds baseBounds, BaseTransform baseTransform) {
        return null;
    }

    @Override
    protected boolean impl_computeContains(double v, double v2) {
        return false;
    }

    @Override
    public Object impl_processMXNode(MXNodeAlgorithm mxNodeAlgorithm, MXNodeAlgorithmContext mxNodeAlgorithmContext) {
        return null;
    }
}
