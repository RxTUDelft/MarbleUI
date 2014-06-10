package org.rxtudelft.marbleui;

import rx.Observable;
import rx.schedulers.TestScheduler;
import rx.subjects.TestSubject;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created by jeff on 10-6-14.
 */
public class TestSchedulerTryout {
    public static void main(String[] args) {
        TestScheduler s = new TestScheduler();
        TestSubject<Long> ts = TestSubject.create(s);

        Stream.iterate(0, (m) -> m + 1).limit(100).forEach((i) -> {
            ts.onNext(Long.valueOf(i), (i+1) * 1000);
        });

        Observable<Integer> b = Observable.from(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
        ts.zip(b, (Long c, Integer d) -> c + d).subscribe((r) -> System.out.println(s.now() + ": " + r));
        s.advanceTimeBy(9, TimeUnit.SECONDS);
    }
}
