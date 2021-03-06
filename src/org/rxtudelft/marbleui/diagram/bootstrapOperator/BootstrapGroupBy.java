package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.ChildObservableModel;
import org.rxtudelft.marbleui.diagram.ComplexObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.TestScheduler;

/**
 * Created by ferdy on 6/20/14.
 */
public class BootstrapGroupBy<K> extends BootstrapOperator1<SimpleMarbleModel, ChildObservableModel> {
    private Func1<? super SimpleMarbleModel, K> groupFunc;

    public BootstrapGroupBy(Func1<? super SimpleMarbleModel, K> groupFunc) {
        super("GroupBy");
        this.groupFunc = groupFunc;
    }

    @Override
    public Observable<ChildObservableModel> call1(TestScheduler s, Observable<SimpleMarbleModel> in1) {
        return in1.groupBy(this.groupFunc).map(observableToModel(s));
    }

    public ComplexObservableModel getOutObservableModel() {
        return new ComplexObservableModel();
    }
}
