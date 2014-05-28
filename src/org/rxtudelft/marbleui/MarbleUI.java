package org.rxtudelft.marbleui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.rxtudelft.marbleui.diagram.MarbleModel;
import org.rxtudelft.marbleui.node.NodeObservable;
import org.rxtudelft.marbleui.node.NodeOperator;
import rx.Observable;
import rx.observables.JavaFxObservable;
import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class MarbleUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static final double width  = 800;
    public static final double height = 600;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MarbleUI");

        stage.setScene(new Scene(new Group(), width, height, Color.WHITE));

        @SuppressWarnings("unchecked")
        PublishSubject<Double>[] clicks = (PublishSubject<Double>[]) new PublishSubject[] {
            PublishSubject.create(), PublishSubject.create(), PublishSubject.create()
        };

        Observable<List<Double>[]> marbles ; {
            Stream<Observable<ArrayList<Double>>> sm = Arrays.stream(clicks).map(ps ->
                    ps.<ArrayList<Double>>scan(new ArrayList<>(), (ArrayList<Double> l, Double d) -> {
                        l.add(d);
                        return l;
                    }));

            List<Observable<ArrayList<Double>>> m = sm.collect(Collectors.toList());

            marbles = Observable.combineLatest(m, args -> (List<Double>[]) new List[]{(List)args[0], (List)args[1], (List)args[2]});
        }

        @SuppressWarnings("unchecked")
        PublishSubject<Double>[] hovers = (PublishSubject<Double>[]) new PublishSubject[] {
                PublishSubject.create(), PublishSubject.create(), PublishSubject.create()
        };

        Observable<Double[]> ghosts = Observable.merge(hovers[0].map(x -> new Double[] {x, null, null})
            , hovers[1].map(x -> new Double[] {null, x, null})
            , hovers[2].map(x -> new Double[] {null, null, x})).startWith(new Double[] {null, null, null});

        Observable<Number> widthObs  = JavaFxObservable.fromObservableValue(stage.widthProperty());
        Observable<Number> heightObs = JavaFxObservable.fromObservableValue(stage.heightProperty());

        double width   = 1000;
        double height  = 800;
        double h       = height / 5;

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        final NodeObservable nObs;
        nObs = new NodeObservable(width, h);
        root.getChildren().add(nObs);

        JavaFxObservable.fromNodeEvents(nObs, MouseEvent.MOUSE_MOVED)
                .subscribe(e -> {
                    nObs.ghostProperty().set(OptionalDouble.of(e.getX()));
                });

        JavaFxObservable.fromNodeEvents(nObs, MouseEvent.MOUSE_CLICKED)
                .subscribe(e -> {
                    nObs.marblesProperty().add(new MarbleModel(e.getX()));
                });

        final NodeOperator nOp = new NodeOperator(width, h, "Test");
        root.getChildren().add(nOp);

        final NodeObservable nObsOut = new NodeObservable(width, h);
        root.getChildren().addAll(nObsOut);

        root.setPadding(new Insets(h / 2));

        stage.getScene().setRoot(root);
        stage.show();

        stage.show();
    }
}
