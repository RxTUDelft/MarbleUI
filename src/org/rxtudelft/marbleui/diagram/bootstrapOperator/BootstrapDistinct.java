package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by ferdy on 6/18/14.
 */
public class BootstrapDistinct implements BootstrapOperator1 {
    @Override
    public Observable<MarbleModel> call1(Observable<MarbleModel> in1) {
        return in1.distinct();
    }
}
