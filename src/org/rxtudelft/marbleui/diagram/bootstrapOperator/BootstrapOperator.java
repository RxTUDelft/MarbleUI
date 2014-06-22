package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Func2;

import java.util.List;

/**
 * Created by ferdy on 5/16/14.
 */
public abstract class BootstrapOperator implements Func2<Scheduler, List<Observable<SimpleMarbleModel>>, Observable<?extends MarbleModel>> {

    public ObservableModel getOutObservableModel() {
        return new ObservableModel();
    }
}
