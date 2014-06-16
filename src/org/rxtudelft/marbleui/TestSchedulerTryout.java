package org.rxtudelft.marbleui;

import rx.Observable;
import rx.schedulers.TestScheduler;
import rx.subjects.TestSubject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created by jeff on 10-6-14.
 */
public class TestSchedulerTryout {
    public static void main(String[] args) {
        //create a test scheduler s to control manually
        TestScheduler s = new TestScheduler();

        //create a test subject to publish events to
        TestSubject<Long> ts = TestSubject.create(s);

        //publish 0...10 on the test schedule 1 second apart from each other
        Stream.iterate(0, (m) -> m + 1).limit(10).forEach((i) -> {
            ts.onNext(Long.valueOf(i), (i+1) * 1000);
        });
        //and complete on second 11
        ts.onCompleted(11 * 1000);

        //create a list of 1's
        Observable<Integer> b = Observable.from(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        //to increment the previously created numbers,
        Observable<String> result = ts.zip(b, (Long c, Integer d) -> c + d)
                //and project them to [time: incrementedNumber(c+d)]
                .map(r -> s.now() + ": " + r);

        //create a list to store these numbers in
        List<String> list = new ArrayList<>();

        //subscribe to the result, write all to the list
        result.subscribe(str -> list.add(str));

        //advance time untill all events should be past
        s.advanceTimeBy(11, TimeUnit.SECONDS);

        //print all numbers
        list.stream().forEach(System.out::println);
    }
}
