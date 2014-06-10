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

/**
 * Created by ferdy on 5/28/14.
 */
public class ObservableModel{       
    public static final int MAX_TIME = 1000;
    private ObservableMap<Long, MarbleModel> marbles;
    private Observable<Timestamped<MarbleModel>> changeObs;

    public ObservableModel() {
        this(new HashMap<>());
    }

    public ObservableModel(Map<Long, MarbleModel> marbles) {
        this.marbles = FXCollections.observableHashMap();
        marbles.forEach((k, v) -> {
            this.marbles.put(k, v);
        });

        this.changeObs = Observable.create((Observable.OnSubscribe<Timestamped<MarbleModel>>) subscriber -> {
            this.marbles.addListener((MapChangeListener<Long, MarbleModel>) (change) -> {
                if (change.wasAdded()) {
                    subscriber.onNext(new Timestamped<>(change.getKey(), change.getValueAdded()));
                }
            });
        });
    }

    public void put(long at, MarbleModel marble) {
        this.marbles.put(at, marble);
    }

    public Map<Long, MarbleModel> getMarbles() {
        return marbles;
    }

    public Observable<Timestamped<MarbleModel>> getChangeObs() {
        return changeObs;
    }

    public Observable<MarbleModel> testSubject(TestScheduler testScheduler) {
        TestSubject<MarbleModel> ret = TestSubject.create(testScheduler);

        marbles.forEach((k,v) -> ret.onNext(v, k));

//        ret.onCompleted(marbles.keySet().stream().max(Comparator.<Long>naturalOrder()).get());

        return ret.asObservable();
    }
}
