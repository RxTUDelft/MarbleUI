package org.rxtudelft.marbleui.diagram;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import rx.Observable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ferdy on 5/28/14.
 */
public class ObservableModel<T extends MarbleModel> {
    private ObservableList<T> marbles;
    private Observable<T> observable;


    public ObservableModel(List<T> marbles) {
        this.marbles = FXCollections.observableArrayList(marbles);

        this.observable = Observable.create((Observable.OnSubscribe<T>) subscriber -> {
            this.marbles.addListener((ListChangeListener<T>) (change) -> {
                change.next();
                change.getAddedSubList().forEach(m -> {
                        subscriber.onNext(m);
                });
            });
        });
    }

    public void add(T m) {
        this.marbles.add(m);
    }

    public ObservableList<T> getMarbles() {
        return marbles;
    }

    public Observable<T> getObservable() {
        return observable;
    }
}
