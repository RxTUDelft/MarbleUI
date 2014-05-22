package org.rxtudelft.marbleui.diagram.initOperator;

import org.rxtudelft.marbleui.diagram.Marble;
import rx.Observable;
import rx.functions.Func1;

import java.util.List;

/**
 * Created by ferdy on 5/16/14.
 */
public class InitMap implements InitOperator1 {

    private Func1<Marble, Marble> mapping;

    public InitMap(Func1<Marble, Marble> mapping) {
        this.mapping = mapping;
    }

    @Override
    public Observable<Marble> call1(Observable<Marble> toMap) {
        return toMap.map(mapping);
    }
}
