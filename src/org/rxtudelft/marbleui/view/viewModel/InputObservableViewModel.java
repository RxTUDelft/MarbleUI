package org.rxtudelft.marbleui.view.viewModel;

import javafx.beans.property.ObjectProperty;
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
    private ObjectProperty<MarbleModel> mode;

    public InputObservableViewModel(ObservableView<T> view) {
        this.view = view;
        this.mode = MarbleDiagramView.mode;
        this.clicks = JavaFxObservable.fromNodeEvents(view.getNode(), MouseEvent.MOUSE_CLICKED);

        this.clicks.subscribe(c -> {
            if(mode.get() instanceof NGonMarbleModel && view instanceof NGonObservableView) {
                view.getModel().put((long) c.getX(), new NGonMarbleModel(
                        MarbleDiagramView.corners.get(),
                        MarbleDiagramView.color.get()));
            }
            else if(mode.get() instanceof ErrorModel) {

                view.getModel().put((long) c.getX(), new ErrorModel());
            }
            else if(mode.get() instanceof CompletedModel) {

                view.getModel().put((long) c.getX(), new CompletedModel());
            }
            else if(mode.get() instanceof ChildObservableModel) {
                view.getModel().put((long) c.getX(), new ChildObservableModel());
            }
            else if(mode.get() instanceof StringMarbleModel) {
                view.getModel().put((long) c.getX(), new StringMarbleModel(((StringMarbleModel)mode.get()).getValue()));
            }
            else {
                System.out.println("Mode: " + MarbleDiagramView.mode.get());
            }
        });

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
