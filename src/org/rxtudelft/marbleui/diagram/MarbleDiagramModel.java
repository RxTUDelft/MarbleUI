package org.rxtudelft.marbleui.diagram;

import org.rxtudelft.marbleui.diagram.initOperator.InitOperator;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by ferdy on 5/8/14.
 */
public class MarbleDiagramModel {
    private List<ObservableModel> observables;
    private InitOperator operator;
    private ObservableModel output;

    public MarbleDiagramModel(List<ObservableModel> observables, InitOperator operator) {
        this.operator = operator;
        this.observables = observables;
        this.output = new ObservableModel(new HashMap<>());
    }
}
