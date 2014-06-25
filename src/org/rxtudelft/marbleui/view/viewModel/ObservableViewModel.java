package org.rxtudelft.marbleui.view.viewModel;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.view.diagram.observable.ObservableView;

/**
 * Created by ferdy on 6/25/14.
 */
public interface ObservableViewModel<T extends MarbleModel> {
    public ObservableView<T> getModel();

}
