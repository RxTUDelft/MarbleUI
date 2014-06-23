package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.*;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by ferdy on 6/20/14.
 */
public class BootstrapWindow extends BootstrapOperator1 {
    private int windowSize;

    public BootstrapWindow(int windowSize) {
        this.windowSize = windowSize;
    }

    @Override
    public Observable<ChildObservableModel> call1(Scheduler s, Observable<SimpleMarbleModel> in1) {
        return in1.window(this.windowSize).map(group -> {
            ChildObservableModel groupModel = new ChildObservableModel();
            group.timestamp().subscribe(marble -> {
                groupModel.put(s.now(), marble.getValue());
            });
            return groupModel;
        });
    }

    public ComplexObservableModel getOutObservableModel() {
        return new ComplexObservableModel();
    }
}