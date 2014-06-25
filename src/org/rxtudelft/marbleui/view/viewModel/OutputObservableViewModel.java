package org.rxtudelft.marbleui.view.viewModel;

import javafx.scene.input.MouseEvent;
import org.rxtudelft.marbleui.diagram.*;
import org.rxtudelft.marbleui.view.diagram.observable.ObservableView;
import rx.Observable;

/**
 * Created by ferdy on 6/25/14.
 */
public class OutputObservableViewModel<T extends MarbleModel> implements ObservableViewModel<T> {
    private ObservableView<T> view;
    private Observable<MouseEvent> clicks;

    public OutputObservableViewModel(ObservableView<T> view) {
        this.view = view;

        this.view.getModel().getChangeObs().subscribe(c -> {
            this.view.clear();
            this.view.getModel().getMarbles().forEach((k, v) -> {
                this.view.placeMarble(k, v);
            });
        });
    }

    @Override
    public ObservableView<T> getModel() {
        return view;
    }
}
