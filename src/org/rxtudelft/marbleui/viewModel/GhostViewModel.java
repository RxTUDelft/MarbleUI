package org.rxtudelft.marbleui.viewModel;

import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import org.rxtudelft.marbleui.view.diagram.GhostMarbleView;
import rx.observables.JavaFxObservable;

/**
 * Created by ferdy on 6/18/14.
 */
public class GhostViewModel {
    private GhostMarbleView view;
    private ObservableIntegerValue n;
    private ObservableValue<Color> color;

    public GhostViewModel(GhostMarbleView view, ObservableIntegerValue n, ObservableValue<Color> color) {
        this.view = view;
        this.n = n;

        JavaFxObservable.fromObservableValue(n).subscribe(newN -> {
                    GhostViewModel.this.view.nProperty().setValue(newN.intValue());
                });

        JavaFxObservable.fromObservableValue(color).subscribe(newColor -> {
                    GhostViewModel.this.view.colorProperty().setValue(newColor);
        });
    }
}
