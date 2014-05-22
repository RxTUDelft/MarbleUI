package org.rxtudelft.marbleui.diagram.initOperator;

import org.rxtudelft.marbleui.diagram.Marble;
import rx.Observable;
import rx.functions.Func1;

import java.util.List;

/**
 * Created by ferdy on 5/16/14.
 */
public class InitFilter implements InitOperator1 {

    private Func1<Marble, Boolean> filter;

    public InitFilter(Func1<Marble, Boolean> filter) {
        this.filter = filter;
    }

    @Override
    public Observable<Marble> call1(Observable<Marble> toFilter) {
        return toFilter.filter(filter);
    }
}
