package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.StringMarbleModel;
import rx.Observable;
import rx.Scheduler;
import rx.observables.StringObservable;

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
    public Observable<StringMarbleModel> call1(Scheduler s, Observable<StringMarbleModel> in1) {
        return StringObservable.split(in1.map(StringMarbleModel::getValue), splitOn).map(subString -> {
            return new StringMarbleModel(subString);
        });
    }


}
