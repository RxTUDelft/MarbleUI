package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by ferdy on 6/18/14.
 */
public class BootstrapDistinct implements BootstrapOperator1<SimpleMarbleModel> {
    @Override
    public Observable<SimpleMarbleModel> call1(Observable<SimpleMarbleModel> in1) {
        return in1.distinct();
    }
}
