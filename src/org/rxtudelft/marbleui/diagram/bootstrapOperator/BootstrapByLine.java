package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.StringMarbleModel;
import rx.Observable;
import rx.observables.StringObservable;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/25/14.
 */
public class BootstrapByLine extends BootstrapStringOperator {
    public BootstrapByLine() {
        super("From");
    }

    @Override
    public Observable<StringMarbleModel> call1(TestScheduler s, Observable<StringMarbleModel> in1) {
        return StringObservable.byLine(in1.map(blobModel -> {
            return blobModel.getValue();
        })).map(line -> {
            return new StringMarbleModel(line.getText());
        });
    }
}
