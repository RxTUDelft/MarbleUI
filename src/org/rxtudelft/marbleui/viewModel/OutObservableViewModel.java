package org.rxtudelft.marbleui.viewModel;

import org.rxtudelft.marbleui.diagram.ObservableModel;
import org.rxtudelft.marbleui.view.diagram.ObservableView;

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
