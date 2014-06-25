package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.StringMarbleModel;
import rx.Observable;
import rx.observables.StringObservable;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/25/14.
 */
public class BootstrapSplit extends BootstrapStringOperator {
    private String splitOn;
    public BootstrapSplit(String splitOn) {
        super("Split");
        this.splitOn = splitOn;
    }

    @Override
    public Observable<StringMarbleModel> call1(TestScheduler s, Observable<StringMarbleModel> in1) {
        return StringObservable.split(in1.map(StringMarbleModel::getValue), splitOn)
                .map(StringMarbleModel::new);
    }


}
