package org.rxtudelft.marbleui.viewModel;

import javafx.beans.value.ObservableValue;
import javafx.scene.input.MouseEvent;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.view.diagram.MarbleView;
import org.rxtudelft.marbleui.view.diagram.ObservableView;
import rx.Observable;
import rx.observables.JavaFxObservable;

import java.util.OptionalDouble;

/**
 * Created by ferdy on 5/28/14.
 */
public class SimpleObservableViewModel {
    public SimpleObservableViewModel(ObservableView view, ObservableModel model) {
        JavaFxObservable.fromNodeEvents(view, MouseEvent.MOUSE_MOVED)
                .subscribe(e -> view.ghostProperty().set(OptionalDouble.of(view.xToMs(e.getX()))));

        JavaFxObservable.fromNodeEvents(view, MouseEvent.MOUSE_CLICKED)
                .subscribe(e -> model.put(view.xToMs(Math.round(e.getX())), view.getGhostMarble().getModel()));

        model.getChangeObs().subscribe(change -> {
            if(change.wasAdded()) {
                view.marblesProperty().put(change.getKey(), change.getValueAdded());
            }

            else {
                view.marblesProperty().remove(change.getKey());
            }
        });
    }
}
