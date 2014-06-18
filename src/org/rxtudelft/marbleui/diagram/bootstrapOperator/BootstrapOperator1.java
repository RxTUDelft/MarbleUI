package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;

import java.util.List;

/**
 * Created by ferdy on 5/22/14.
 */
public interface BootstrapOperator1<T extends MarbleModel> extends BootstrapOperator<T> {
    public default Observable<T> call(List<Observable<SimpleMarbleModel>> observables) {
        return this.call1(observables.get(0));
    }

    public abstract Observable<T> call1(Observable<SimpleMarbleModel> in1);
}
