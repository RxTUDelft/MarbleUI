package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.StringMarbleModel;
import org.rxtudelft.marbleui.diagram.StringObservableModel;
import rx.Observable;
import rx.Scheduler;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ferdy on 6/25/14.
 */
public abstract class BootstrapStringOperator extends BootstrapOperator1<StringMarbleModel, StringMarbleModel> {

    protected BootstrapStringOperator(String label) {
        super(label);
    }

    public ObservableModel getOutObservableModel() {
        return new StringObservableModel();
    }

    public List<ObservableModel> getInObservableModels() {
        List<ObservableModel> inObs = new LinkedList<>();
        inObs.add(new StringObservableModel());

        return inObs;
    }
}
