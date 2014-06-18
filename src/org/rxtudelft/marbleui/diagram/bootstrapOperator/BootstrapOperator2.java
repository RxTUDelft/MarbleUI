package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import rx.Observable;

import java.util.List;

/**
 * Created by ferdy on 5/22/14.
 */
public interface BootstrapOperator2 extends BootstrapOperator {
    public default Observable<MarbleModel> call(List<Observable<MarbleModel>> observables) {
        return this.call2(observables.get(0), observables.get(1));
    }

    public abstract Observable<MarbleModel> call2(Observable<MarbleModel> in1, Observable<MarbleModel> in2);
}
