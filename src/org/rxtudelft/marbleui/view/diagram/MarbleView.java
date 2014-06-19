package org.rxtudelft.marbleui.view.diagram;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;

/**
 * Abstract class for everything that can be a marble.
 * Can be simple or or composite
 */
public abstract class MarbleView extends Node {


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
