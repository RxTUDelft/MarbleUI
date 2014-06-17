package org.rxtudelft.marbleui.viewModel;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import org.rxtudelft.marbleui.view.diagram.ObservableView;
import rx.observables.JavaFxObservable;

import java.util.OptionalDouble;

/**
 * Created by ferdy on 5/28/14.
 */
public class InObservableViewModel {
    public InObservableViewModel(ObservableView view, ObservableModel model) {
        JavaFxObservable.fromNodeEvents(view, MouseEvent.MOUSE_MOVED)
                .subscribe(e -> {
                    view.ghostProperty().set(OptionalDouble.of(view.xToMs(e.getX())));
                });

        JavaFxObservable.fromNodeEvents(view, MouseEvent.MOUSE_CLICKED)
                .subscribe(e -> {
                    int n = view.getGhostMarble().getN();
                    model.put(view.xToMs(Math.round(e.getX())), new SimpleMarbleModel(n, Color.DARKORCHID));
                });

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
