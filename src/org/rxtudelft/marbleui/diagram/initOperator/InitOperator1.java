package org.rxtudelft.marbleui.diagram.initOperator;

import org.rxtudelft.marbleui.diagram.Marble;
import rx.Observable;
import rx.functions.Func1;

import java.util.List;

/**
 * Created by ferdy on 5/22/14.
 */
public interface InitOperator1 extends InitOperator {
    public default Observable<Marble> call(List<Observable<Marble>> observables) {
        return this.call1(observables.get(0));
    }

    public abstract Observable<Marble> call1(Observable<Marble> in1);
}
