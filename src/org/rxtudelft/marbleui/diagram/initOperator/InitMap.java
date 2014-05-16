package org.rxtudelft.marbleui.diagram.initOperator;

import org.rxtudelft.marbleui.diagram.Marble;
import rx.Observable;
import rx.functions.Func1;

import java.util.List;

/**
 * Created by ferdy on 5/16/14.
 */
public class InitMap extends InitOperator {

    private Func1<Marble, Marble> mapping;

    public InitMap(Func1<Marble, Marble> mapping) {
        this.mapping = mapping;
    }

    @Override
    public Observable<Marble> call(List<Observable<Marble>> observables) {
        Observable<Marble> from = observables.get(0);
        return from.map(mapping);
    }
}
