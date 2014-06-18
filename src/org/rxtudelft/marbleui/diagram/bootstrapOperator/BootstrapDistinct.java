package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModelFactory;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by ferdy on 6/18/14.
 */
public class BootstrapDistinct extends BootstrapOperator1<SimpleMarbleModel> {

    public BootstrapDistinct(ObservableModelFactory<SimpleMarbleModel> outObservableModelFactory) {
        super(outObservableModelFactory);
    }

    @Override
    public Observable<SimpleMarbleModel> call1(Observable<SimpleMarbleModel> in1) {
        return in1.distinct();
    }
}
