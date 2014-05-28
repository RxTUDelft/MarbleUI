package org.rxtudelft.marbleui.diagram;

import org.rxtudelft.marbleui.diagram.initOperator.InitOperator;

import java.util.*;

/**
 * Created by ferdy on 5/8/14.
 */
public class MarbleDiagramModel {
    private List<ObservableModel<MarbleModel>> observables;
    private InitOperator operator;

    public MarbleDiagramModel(List<ObservableModel<MarbleModel>> observables, InitOperator operator) {
        this.operator = operator;
        this.observables = observables;
    }
}
