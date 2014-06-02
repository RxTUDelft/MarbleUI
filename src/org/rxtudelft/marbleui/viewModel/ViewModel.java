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
    private Object model;

    public ViewModel(Observable<T> state, Node view, Iterable<ObservableValue<?>> watch, Object model) {
        this.state = state;
        this.view = view;
        this.model = model;

        watch.forEach(o -> {
            o.addListener((ov, oldVal, nevVal) -> this.updateModel());
        });
    }

    public Observable<T> getState() {
        return state;
    }

    public Node getView() {
        return view;
    }

    public Object getModel() {
        return model;
    }

    //update the model on changes in the view
    abstract public void updateModel();
}
