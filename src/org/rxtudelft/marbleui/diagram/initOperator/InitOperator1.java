package org.rxtudelft.marbleui.diagram.initOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import rx.Observable;

import java.util.List;

/**
 * Created by ferdy on 5/22/14.
 */
public interface InitOperator1 extends InitOperator {
    public default Observable<MarbleModel> call(List<Observable<MarbleModel>> observables) {
        return this.call1(observables.get(0));
    }

    public abstract Observable<MarbleModel> call1(Observable<MarbleModel> in1);
}
