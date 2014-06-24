package org.rxtudelft.marbleui.viewModel;

import javafx.beans.value.ObservableValue;
import javafx.scene.input.MouseEvent;
import org.rxtudelft.marbleui.diagram.ComplexObservableModel;
import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.diagram.SimpleObservableView;
import org.rxtudelft.marbleui.view.diagram.*;
import rx.Observable;
import rx.observables.JavaFxObservable;

import java.util.OptionalDouble;

/**
 * Created by ferdy on 5/28/14.
 */
public class ObservableViewModel {
    public ObservableViewModel(ObservableView view, Observable<MarbleView> mode) {
        ObservableModel model = view.getModel();
        JavaFxObservable.fromNodeEvents(view, MouseEvent.MOUSE_MOVED)
                .subscribe(e -> view.ghostProperty().set(OptionalDouble.of(view.xToMs(e.getX()))));

        Observable.combineLatest(JavaFxObservable.fromNodeEvents(view, MouseEvent.MOUSE_CLICKED), mode, (e, m) -> {
            //always pass on terminal marbles
            if (m instanceof ErrorView || m instanceof CompletedView) {
                model.put(view.xToMs(Math.round(e.getX())), view.getGhostMarble().getModel());
            }
            //simple view? pass simple marbles
            else if (view instanceof SimpleObservableView && m instanceof SimpleMarbleView) {
                model.put(view.xToMs(Math.round(e.getX())), view.getGhostMarble().getModel());
            }
            //complex view? pass child marbles
            else if (view instanceof ComplexObservableView && m instanceof ChildObservableView) {
                model.put(view.xToMs(Math.round(e.getX())), view.getGhostMarble().getModel());
            }

            return null;
        }).subscribe();

        model.getChangeObs().subscribe(change -> {
            if(change.wasAdded()) {
                view.marblesProperty().put(change.getKey(), change.getValueAdded());
            }

            else {
                view.marblesProperty().remove(change.getKey());
            }
        });

        //auto subscribe to child obs
        if (model instanceof ComplexObservableModel) {
           ComplexObservableModel complexModel = (ComplexObservableModel) model;
            complexModel.getChangeObs()
        }
    }
}
