package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;

/**
 * Created by ferdy on 6/18/14.
 */
public class BootstrapDistinct extends BootstrapOperator1 {

    @Override
    public Observable<SimpleMarbleModel> call1(Observable<SimpleMarbleModel> in1) {
        return in1.distinct();
    }
}
