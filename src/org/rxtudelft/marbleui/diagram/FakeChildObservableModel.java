package org.rxtudelft.marbleui.diagram;

import javafx.collections.MapChangeListener;
import rx.Observable;
import rx.schedulers.TestScheduler;
import rx.subjects.TestSubject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by jeff on 25-6-14.
 */
public class FakeChildObservableModel extends ChildObservableModel {
    private final Observable<MarbleModel> observable;

    public FakeChildObservableModel(Observable<MarbleModel> observable) {
        this.observable = observable;
    }

    public Observable<MarbleModel> getObservable() {
        return observable;
    }

    public FakeChildObservableModel() {
        throw new NotImplementedException();
    }

    @Override
    public void put(long at, MarbleModel marble) {
        throw new NotImplementedException();
    }

    @Override
    public Observable<MapChangeListener.Change<Long, MarbleModel>> getChangeObs() {
        throw new NotImplementedException();
    }

    @Override
    public TestSubject<MarbleModel> testSubject(TestScheduler testScheduler) {
        throw new NotImplementedException();
    }

    @Override
    public void clear() {
        throw new NotImplementedException();
    }
}
