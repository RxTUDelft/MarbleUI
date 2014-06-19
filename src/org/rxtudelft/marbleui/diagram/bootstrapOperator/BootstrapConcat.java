package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import rx.Observable;

/**
 * Created by ferdy on 6/19/14.
 */
public class BootstrapConcat extends BootstrapOperator2 {

    @Override
    public Observable call2(Observable in1, Observable in2) {
        return Observable.concat(in1, in2);
    }
}
