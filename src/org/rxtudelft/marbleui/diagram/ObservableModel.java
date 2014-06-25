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
import java.util.concurrent.CompletionException;

import static javafx.collections.MapChangeListener.Change;
import static rx.Observable.OnSubscribe;

/**
 * Created by ferdy on 5/28/14.
 */
public class ObservableModel {
    public static final int MAX_TIME = 1000;
    private ObservableMap<Long, MarbleModel> marbles;
    private Observable<Change<Long, MarbleModel>> changeObs;

    private Timestamped<SimpleMarbleModel> end = new Timestamped<>(MAX_TIME, new CompletedModel());

    public ObservableModel() {
        this(new HashMap<>());
    }

    public ObservableModel(Map<Long, MarbleModel> marbles) {
        this.marbles = FXCollections.observableHashMap();
        marbles.forEach(this.getMarbles()::put);

        this.changeObs = Observable.create((OnSubscribe<Change<Long, MarbleModel>>) subscriber -> {
            ObservableModel.this.marbles.addListener((MapChangeListener<Long, MarbleModel>) (change) -> {
                subscriber.onNext((Change<Long, MarbleModel>)change);
            });
        });
    }

    public void put(long at, MarbleModel marble) {
        if (marble instanceof ErrorModel || marble instanceof CompletedModel) {
            this.marbles.remove(this.end.getTimestampMillis());
            this.end = new Timestamped<>(at, (SimpleMarbleModel) marble);
        }
        this.getMarbles().put(at, marble);
    }

    public Map<Long, MarbleModel> getMarbles() {
        return marbles;
    }

    public Observable<Change<Long, MarbleModel>> getChangeObs() {
        return changeObs;
    }

    public Observable<MarbleModel> testSubject(TestScheduler testScheduler) {
        TestSubject<MarbleModel> ret = TestSubject.create(testScheduler);

        marbles.forEach((k,v) -> {
            if(v instanceof SimpleMarbleModel) {
                ret.onNext(((SimpleMarbleModel) v), k);
            } else if(v instanceof CompletedModel) {
                ret.onCompleted(k);
            } else if(v instanceof ErrorModel) {
                ret.onError(new Throwable(), k);
            }
            else if (v instanceof ChildObservableModel) {
                System.out.println("put complex model on subject");
                ret.onNext(v);
            }
        });

        ret.onCompleted(MAX_TIME);

        return ret.asObservable();
    }

    public void clear() {
        this.marbles.clear();
    }
}
