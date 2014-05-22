package org.rxtudelft.marbleui;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import rx.Observable;
import rx.observables.JavaFxObservable;
import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        PublishSubject<MouseEvent>[] obs = (PublishSubject<MouseEvent>[]) new PublishSubject[] {
            PublishSubject.create(), PublishSubject.create(), PublishSubject.create()
        };

        @SuppressWarnings("unchecked")
        Observable<List<Double>>[] marbles = Arrays.stream(obs)
                .map((PublishSubject<MouseEvent> ps) ->
                        ps.asObservable()
                                .map(MouseEvent::getX)
                                .scan(new ArrayList<Double>(), (l, d) -> {
                                    l.add(d);
                                    return l;
                                }))
                .collect(Collectors.toList()).toArray(new Observable[0]);



        Observable.combineLatest( JavaFxObservable.fromObservableValue(stage.widthProperty())
                                , JavaFxObservable.fromObservableValue(stage.heightProperty())
                                , marbles[0]
                                , marbles[1]
                                , marbles[2]
                                , (numW,numH,m1,m2,m3) -> {
            double width   = numW.doubleValue();
            double height  = numH.doubleValue();
            double h       = height / 5;

            VBox root = new VBox();
            root.setSpacing(0);

            Node n = observable(width, h, m1);
            JavaFxObservable.fromNodeEvents(n, MouseEvent.MOUSE_CLICKED).subscribe(obs[0]);
            root.getChildren().addAll(n);

            n = observable(width, h, m2);
            JavaFxObservable.fromNodeEvents(n, MouseEvent.MOUSE_CLICKED).subscribe(obs[1]);
            root.getChildren().addAll(n);

            n = operator(width, h, "Test");
            root.getChildren().add(n);

            n = observable(width, h, m3);
            JavaFxObservable.fromNodeEvents(n, MouseEvent.MOUSE_CLICKED).subscribe(obs[2]);
            root.getChildren().addAll(n);

            return root;

        }).subscribe((r) -> {
            stage.getScene().setRoot(r);
            stage.show();
        });

        stage.show();
    }

    public static Node observable(double width, double height, List<Double> marbles) {
        Group observable = new Group();

        double padding = height/2;

        // not sure if this is the best solution to force the group to have this width/height but it works.
        Rectangle background = new Rectangle(0, 0, width, height);
        background.setFill(Color.TRANSPARENT);

        Line line = new Line(0, 0, width-2*padding, 0);

        line.setStrokeType(StrokeType.CENTERED);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);

        line.setTranslateX(padding);
        line.setTranslateY(padding);

        List<Polygon> pentagons = marbles.stream()
                .map((x) -> {
            Polygon pentagon = NGon.pointUp(0, padding, 5, 0.8 * padding);

            pentagon.setStrokeType(StrokeType.INSIDE);
            pentagon.setStroke(Color.BLACK);
            pentagon.setStrokeWidth(2);
            pentagon.setFill(Color.TRANSPARENT);

            pentagon.setTranslateX(x);

            return pentagon;
        }).collect(Collectors.toList());

        observable.getChildren().addAll(background, line);
        observable.getChildren().addAll(pentagons);

        return observable;
    }

    public static Node operator(double w, double h, String name) {
        StackPane operator = new StackPane();

        double p = h/2; // padding

        Rectangle rectangle = new Rectangle(p, p, w-2*p, h);

        rectangle.setStrokeType(StrokeType.CENTERED);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2);
        rectangle.setFill(Color.TRANSPARENT);

        Text text = new Text(name);

        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextOrigin(VPos.CENTER);

        operator.getChildren().addAll(rectangle, text);

        return operator;
    }
}
