package org.rxtudelft.marbleui.viewModel;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleMarbleModel;
import org.rxtudelft.marbleui.node.NodeObservable;
import rx.observables.JavaFxObservable;

import java.util.OptionalDouble;

/**
 * Created by ferdy on 5/28/14.
 */
public class ObservableViewModel {
    public ObservableViewModel(NodeObservable view, ObservableModel model, boolean ghost) {

        if (ghost) {
            JavaFxObservable.fromNodeEvents(view, MouseEvent.MOUSE_MOVED)
                    .subscribe(e -> {
                        view.ghostProperty().set(OptionalDouble.of(e.getX()));
                    });
        }

        JavaFxObservable.fromNodeEvents(view, MouseEvent.MOUSE_CLICKED)
                .subscribe(e -> {
                    model.put(Math.round(e.getX()), new SimpleMarbleModel(5, Color.DARKORCHID));
                });

        model.getChangeObs().subscribe(tm -> {
            view.marblesProperty().put(tm.getTimestampMillis(), tm.getValue());
        });
    }
}
