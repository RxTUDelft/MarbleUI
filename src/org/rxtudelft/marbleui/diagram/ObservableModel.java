package org.rxtudelft.marbleui.diagram;

import javafx.collections.*;
import rx.Observable;
import rx.schedulers.Timestamped;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ferdy on 5/28/14.
 */
public class ObservableModel{
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
}
