package org.rxtudelft.marbleui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.rxtudelft.marbleui.node.NodeObservable;
import org.rxtudelft.marbleui.node.NodeOperator;
import rx.Observable;
import rx.observables.JavaFxObservable;
import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        Observable.combineLatest(widthObs, heightObs, marbles, ghosts, (numW,numH,m,g) -> {
            double width   = numW.doubleValue();
            double height  = numH.doubleValue();
            double h       = height / 5;

            VBox root = new VBox();
            root.setAlignment(Pos.CENTER);

            Node n;
            n = new NodeObservable(0, width, h, m, g[0], clicks, hovers);
            root.getChildren().addAll(n);

            n = new NodeObservable(1, width, h, m, g[1], clicks, hovers);
            root.getChildren().addAll(n);

            n = new NodeOperator(width, h, "Test");
            root.getChildren().add(n);

            n = new NodeObservable(2, width, h, m, g[2], clicks, hovers);
            root.getChildren().addAll(n);

            root.setPadding(new Insets(h / 2));

            return root;

        }).subscribe((r) -> {
            stage.getScene().setRoot(r);
            stage.show();
        });

        stage.show();
    }
}
