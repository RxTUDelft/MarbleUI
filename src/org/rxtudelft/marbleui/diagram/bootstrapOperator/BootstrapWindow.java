package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.ChildObservableModel;
import org.rxtudelft.marbleui.diagram.ComplexObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/20/14.
 */
public class BootstrapWindow extends BootstrapOperator1<SimpleMarbleModel, ChildObservableModel> {
    private int windowSize;

    public BootstrapWindow(int windowSize) {
        super("Window");
        this.windowSize = windowSize;
    }

    @Override
    public Observable<ChildObservableModel> call1(TestScheduler s, Observable<SimpleMarbleModel> in1) {
        return in1.window(this.windowSize).map(observableToModel(s));
    }

    public ComplexObservableModel getOutObservableModel() {
        return new ComplexObservableModel();
    }
}
