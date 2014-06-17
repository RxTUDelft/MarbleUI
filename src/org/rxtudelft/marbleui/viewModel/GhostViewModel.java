package org.rxtudelft.marbleui.viewModel;

import javafx.beans.value.ObservableIntegerValue;
import org.rxtudelft.marbleui.view.diagram.GhostMarbleView;
import rx.observables.JavaFxObservable;

/**
 * Created by ferdy on 6/18/14.
 */
public class GhostViewModel {
    private GhostMarbleView view;
    private ObservableIntegerValue n;

    public GhostViewModel(GhostMarbleView view, ObservableIntegerValue n) {
        this.view = view;
        this.n = n;

        JavaFxObservable.fromObservableValue(n).subscribe(newN -> {
                    GhostViewModel.this.view.nProperty().setValue(newN.intValue());
                });
    }
}
