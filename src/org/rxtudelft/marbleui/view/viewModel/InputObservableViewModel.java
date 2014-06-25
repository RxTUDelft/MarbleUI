package org.rxtudelft.marbleui.view.viewModel;

import javafx.scene.input.MouseEvent;
import org.rxtudelft.marbleui.diagram.*;
import org.rxtudelft.marbleui.diagram.NGonMarbleModel;
import org.rxtudelft.marbleui.view.diagram.MarbleDiagramView;
import org.rxtudelft.marbleui.view.diagram.observable.NGonObservableView;
import org.rxtudelft.marbleui.view.diagram.observable.ObservableView;
import rx.Observable;
import rx.observables.JavaFxObservable;

/**
 * Created by ferdy on 6/25/14.
 */
public class InputObservableViewModel<T extends MarbleModel> implements ObservableViewModel<T> {
    private ObservableView<T> view;
    private Observable<MouseEvent> clicks;

    public InputObservableViewModel(ObservableView<T> view) {
        this.view = view;
        this.clicks = JavaFxObservable.fromNodeEvents(view.getNode(), MouseEvent.MOUSE_CLICKED);

        this.clicks.subscribe(c -> {
            System.out.println(MarbleDiagramView.mode.get());
            if(MarbleDiagramView.mode.get() instanceof NGonMarbleModel && view instanceof NGonObservableView) {
                view.getModel().put((long) c.getX(), new NGonMarbleModel(
                        MarbleDiagramView.corners.get(),
                        MarbleDiagramView.color.get()));
            }
            else if(MarbleDiagramView.mode.get() instanceof ErrorModel) {

                view.getModel().put((long) c.getX(), new ErrorModel());
            }
            else if(MarbleDiagramView.mode.get() instanceof CompletedModel) {

                view.getModel().put((long) c.getX(), new CompletedModel());
            }
            else if(MarbleDiagramView.mode.get() instanceof ChildObservableModel) {
                view.getModel().put((long) c.getX(), new ChildObservableModel());
            }
            else {
                System.out.println("Mode: " + MarbleDiagramView.mode.get());
            }
        });

        this.view.getModel().getChangeObs().subscribe(c -> {
            if(c.wasAdded()) {
                view.placeMarble(c.getKey(), c.getValueAdded());
            }
        });
    }

    @Override
    public ObservableView<T> getModel() {
        return view;
    }
}
