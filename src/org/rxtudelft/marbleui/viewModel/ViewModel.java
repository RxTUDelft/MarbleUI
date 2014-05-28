package org.rxtudelft.marbleui.viewModel;

import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import rx.Observable;

/**
 * Created by ferdy on 5/28/14.
 */
abstract
public class ViewModel<T> {
    private Observable<T> state;
    private Node view;

    public ViewModel(Observable<T> state, Node view, Iterable<ObservableValue<?>> watch) {
        this.state = state;
        this.view = view;

        watch.forEach(o -> {
            o.addListener((ov, oldVal, nevVal) -> this.updateModel());
        });
    }

    //update the model on changes in the view
    abstract public void updateModel();
}
