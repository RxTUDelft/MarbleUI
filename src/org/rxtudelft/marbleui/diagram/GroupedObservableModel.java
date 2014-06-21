package org.rxtudelft.marbleui.diagram;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ferdy on 6/20/14.
 */
public class GroupedObservableModel<K> extends ObservableModel implements MarbleModel {

    private K key;

    public GroupedObservableModel(K key) {
        this(new HashMap<>(), key);
    }

    public GroupedObservableModel(Map<Long, MarbleModel> marbles, K key) {
        super(marbles);
        this.key = key;
    }

    public K getKey() {
        return this.key;
    }
}
