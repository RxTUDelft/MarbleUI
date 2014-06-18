package org.rxtudelft.marbleui;

import rx.Observable;
import rx.observables.GroupedObservable;

import java.lang.Boolean;
import java.lang.Integer;

/**
 * Created by ferdy on 6/18/14.
 */
public class GroupByTryout {
    public static void main(String[] args) {
        Observable<GroupedObservable<Boolean,Integer>> grouped = Observable.from(0, 1, 2, 3).groupBy(i -> {
            return i % 2 == 0;
        });

        grouped.subscribe(g -> {
            System.out.println(g.getKey());
        });
    }
}
