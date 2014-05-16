package org.rxtudelft.marbleui.diagram;

import javafx.util.Pair;
import org.rxtudelft.marbleui.diagram.initOperator.InitMap;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by ferdy on 5/8/14.
 */
public class MarbleDiagram {
    private List<Subject<Marble, Marble>> is;
    private Observable<Marble> o;
    private Map<Long, Pair<Integer, Marble>> inMarbles;
    private Scheduler scheduler;

    public MarbleDiagram(int nI, Func1<List<Observable<Marble>>, Observable<Marble>> initOperator,
                         Map<Long, Pair<Integer, Marble>> inMarbles) {
        this.is = new ArrayList<>(nI);

        for (int i = 0; i < nI; i++) {
            Subject s = PublishSubject.create();
            s.subscribe(in -> {
                System.out.println("in: " + in);
            });
            this.is.add(s);
        }

        this.o = initOperator.call(this.is.stream().map(s -> s.asObservable()).collect(Collectors.toList()));
        this.o.subscribe(s -> {
            System.out.println("out:" + s);
        });

        this.inMarbles = new HashMap<>(inMarbles);
        this.scheduler = Schedulers.immediate();
    }

    public void schedule() {
        //sort by keys
        ArrayList<Long> ks = new ArrayList<>(this.inMarbles.keySet());
        Collections.sort(ks);

        ks.forEach(delay -> {
            Pair<Integer, Marble> kv = this.inMarbles.get(delay);
            Integer i = kv.getKey();
            Marble m = kv.getValue();

            scheduler.createWorker().schedule(() -> {
                MarbleDiagram.this.is.get(i).onNext(m);
            }, delay, TimeUnit.MILLISECONDS);
        });


    }

    public static void main(String[] args) {
        Map<Long, Pair<Integer, Marble>> marbles = new HashMap<>();
        marbles.put(0L, new Pair<>(0, new Marble("from")));

        MarbleDiagram d = new MarbleDiagram(1, new InitMap(m -> new Marble(m.getName() + " mapped to ...")), marbles);
        d.schedule();
    }
}
