package org.rxtudelft.marbleui.diagram;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import rx.Observable;
import rx.schedulers.TestScheduler;
import rx.schedulers.Timestamped;
import rx.subjects.TestSubject;

import java.util.HashMap;
import java.util.Map;

import static javafx.collections.MapChangeListener.Change;
import static rx.Observable.OnSubscribe;

/**
 * Created by ferdy on 5/28/14.
 */
public class ObservableModel<T extends MarbleModel> {
    public static final int MAX_TIME = 1000;
    private ObservableMap<Long, T> marbles;
    private Observable<Change<Long, T>> changeObs;

    public ObservableModel() {
        this(new HashMap<>());
    }

    public ObservableModel(Map<Long, T> marbles) {
        this.marbles = FXCollections.observableHashMap();
        marbles.forEach((k, v) -> {
            this.marbles.put(k, v);
        });

        this.changeObs = Observable.create((OnSubscribe<Change<Long, T>>) subscriber -> {
            ObservableModel.this.marbles.addListener((MapChangeListener<Long, T>) (change) -> {
                //FIXME why does this need a cast?
                subscriber.onNext((Change<Long, T>)change);
            });
        });
    }

    public void put(long at, T marble) {
        this.marbles.put(at, marble);
    }

    public Map<Long, T> getMarbles() {
        return marbles;
    }

    public Observable<Change<Long, T>> getChangeObs() {
        return changeObs;
    }

    public Observable<T> testSubject(TestScheduler testScheduler) {
        TestSubject<T> ret = TestSubject.create(testScheduler);

        marbles.forEach((k,v) -> ret.onNext(v, k));

        ret.onCompleted(MAX_TIME);

        return ret.asObservable();
    }

    public void clear() {
        this.marbles.clear();
    }
}
