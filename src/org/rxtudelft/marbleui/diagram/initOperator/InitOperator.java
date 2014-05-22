package org.rxtudelft.marbleui.diagram.initOperator;

import org.rxtudelft.marbleui.diagram.Marble;
import rx.Observable;
import rx.functions.Func1;

import java.util.List;

/**
 * Created by ferdy on 5/16/14.
 */
public interface InitOperator extends Func1<List<Observable<Marble>>, Observable<Marble>> {
}
