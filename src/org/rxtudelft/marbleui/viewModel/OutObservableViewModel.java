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
public class OutObservableViewModel {
    public OutObservableViewModel(ObservableView view, ObservableModel model) {
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
