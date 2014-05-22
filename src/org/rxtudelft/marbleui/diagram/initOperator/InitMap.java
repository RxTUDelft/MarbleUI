package org.rxtudelft.marbleui.diagram.initOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ferdy on 5/16/14.
 */
public class InitMap implements InitOperator1 {

    private Func1<MarbleModel, MarbleModel> mapping;

    public InitMap(Func1<MarbleModel, MarbleModel> mapping) {
        this.mapping = mapping;
    }

    @Override
    public Observable<MarbleModel> call1(Observable<MarbleModel> toMap) {
        return toMap.map(mapping);
    }
}
