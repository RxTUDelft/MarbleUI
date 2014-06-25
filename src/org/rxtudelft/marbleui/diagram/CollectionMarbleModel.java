package org.rxtudelft.marbleui.diagram;

import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.diagram.NGonMarbleModel;

import java.util.List;

/**
 * Created by ferdy on 6/25/14.
 */
public class CollectionMarbleModel implements MarbleModel {
    private List<NGonMarbleModel> items;

    public CollectionMarbleModel(List<NGonMarbleModel> items) {
        this.items = items;
    }

    public List<NGonMarbleModel> getItems() {
        return items;
    }
}
