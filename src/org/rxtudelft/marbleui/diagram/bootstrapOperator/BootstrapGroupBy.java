package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.*;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.schedulers.TestScheduler;
import rx.schedulers.Timestamped;

import java.util.concurrent.TimeUnit;

/**
 * Created by ferdy on 6/20/14.
 */
public class BootstrapGroupBy<K> extends BootstrapOperator1 {
    private Func1<? super SimpleMarbleModel, K> groupFunc;

    public BootstrapGroupBy(Func1<? super SimpleMarbleModel, K> groupFunc) {
        this.groupFunc = groupFunc;
    }

    @Override
    public Observable<GroupedObservableModel> call1(Scheduler s, Observable<SimpleMarbleModel> in1) {
        return in1.groupBy(this.groupFunc).map(group -> {
            GroupedObservableModel groupModel = new GroupedObservableModel<K>(group.getKey());
            group.timestamp().subscribe(marble -> {
                groupModel.put(s.now(), marble.getValue());
            });
            return groupModel;
        });
    }

    public ComplexObservableModelFactory getOutObservableModelFactory() {
        return new ComplexObservableModelFactory();
    }
}
