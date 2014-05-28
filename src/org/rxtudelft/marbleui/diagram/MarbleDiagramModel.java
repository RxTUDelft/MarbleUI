package org.rxtudelft.marbleui.diagram;

import javafx.util.Pair;
import org.rxtudelft.marbleui.diagram.initOperator.InitMap;
import org.rxtudelft.marbleui.diagram.initOperator.ObservableModel;
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
public class MarbleDiagramModel {
    private List<ObservableModel<MarbleModel>> observables;
}
