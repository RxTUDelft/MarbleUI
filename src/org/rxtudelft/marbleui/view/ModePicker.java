package org.rxtudelft.marbleui.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import org.rxtudelft.marbleui.view.diagram.MarbleView;

/**
 * Created by jeff on 19-6-14.
 */
public class ModePicker extends HBox {
    private ObjectProperty<MarbleView> current;

    public ModePicker(MarbleView first, MarbleView... marbleViews) {
        DrawButton b = new DrawButton(50, 50, first.turnGhost());
        b.clickObs.subscribe(c -> current.setValue(first));
        this.getChildren().add(b);
        current = new SimpleObjectProperty<>(first);

        for (MarbleView marbleView : marbleViews) {
            marbleView.turnGhost();
            b = new DrawButton(50, 50, marbleView);
            b.clickObs.subscribe(c -> current.setValue(marbleView));
            this.getChildren().add(b);
        }
    }

    public ObservableValue<MarbleView> ghostProperty() {
        return current;
    }
}
