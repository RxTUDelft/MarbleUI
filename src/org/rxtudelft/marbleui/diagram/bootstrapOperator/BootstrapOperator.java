package org.rxtudelft.marbleui.diagram.bootstrapOperator;

import org.rxtudelft.marbleui.diagram.ChildObservableModel;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.TestScheduler;

import java.util.List;

/**
 * Created by ferdy on 5/16/14.
 */
public abstract class BootstrapOperator<I extends MarbleModel, O extends MarbleModel> implements
        Func2<TestScheduler, List<Observable<I>>, Observable<O>> {

    private String label;

    protected BootstrapOperator(String label) {
        this.label = label;
    }

    @Override
    public abstract Observable<O> call(TestScheduler s, List<Observable<I>> is);

    protected static Func1<Observable<? extends MarbleModel>, ChildObservableModel> observableToModel(TestScheduler s) {
        return group -> {
            ChildObservableModel groupModel = new ChildObservableModel();
            group.subscribe(marble -> groupModel.put(s.now(), marble));
            return groupModel;
        };
    }

    public ObservableModel getOutObservableModel() {
        return new ObservableModel();
    }

    protected static Func1<ChildObservableModel, Observable<MarbleModel>> modelToObservable(TestScheduler s) {
        return inGroup -> inGroup.testSubject(s);
    }

    abstract public List<ObservableModel> getInObservableModels();

    public String getLabel() {
        return this.label;
    }

    public String toString() {
        return this.getLabel();
    }
}
