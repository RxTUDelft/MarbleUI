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
import org.rxtudelft.marbleui.diagram.GhostMarble;
import rx.Observable;
import rx.observables.JavaFxObservable;
import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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

        Observable<GhostMarble> ghosts = Observable.merge(hovers[0].map(x -> new GhostMarble(0, x))
            , hovers[1].map(x -> new GhostMarble(1, x))
            , hovers[2].map(x -> new GhostMarble(2, x))).startWith(new GhostMarble(-1, 0));

        Observable.combineLatest( JavaFxObservable.fromObservableValue(stage.widthProperty())
                                , JavaFxObservable.fromObservableValue(stage.heightProperty())
                                , marbles
                                , ghosts
                                , (numW,numH,m,g) -> {
            double width   = numW.doubleValue();
            double height  = numH.doubleValue();
            double h       = height / 5;

            VBox root = new VBox();
            root.setSpacing(0);

            Node n;
            n = observable(width, h, m, g, 0, clicks, hovers);
            root.getChildren().addAll(n);

            n = observable(width, h, m, g, 1, clicks, hovers);
            root.getChildren().addAll(n);

            n = operator(width, h, "Test");
            root.getChildren().add(n);

            n = observable(width, h, m, g, 2, clicks, hovers);
            root.getChildren().addAll(n);

            return root;

        }).subscribe((r) -> {
            stage.getScene().setRoot(r);
            stage.show();
        });

        stage.show();
    }

    private static Node observable(double width, double h, List<Double>[] m, GhostMarble g, int i, PublishSubject<Double>[] clicks, PublishSubject<Double>[] hovers) {
        Node n;
        if(g.getObs() == i) {
            n = observable(width, h, m[i], g.getX());
        } else {
            n = observable(width, h, m[i]);
        }
        JavaFxObservable.fromNodeEvents(n, MouseEvent.MOUSE_CLICKED)
                .map(MouseEvent::getX)
                .map(x -> Math.min(width - h / 2, Math.max(h / 2, x)))
                .subscribe(clicks[i]);
        JavaFxObservable.fromNodeEvents(n, MouseEvent.MOUSE_MOVED)
                .map(MouseEvent::getX)
                .map(x -> Math.min(width - h / 2, Math.max(h / 2, x)))
                .subscribe(hovers[i]);
        return n;
    }

    public static Node observable(double width, double height, List<Double> marbles) {
        Group observable = new Group();

        double padding = height/2;

        // not sure if this is the best solution to force the group to have this width/height but it works.
        Rectangle background = new Rectangle(0, 0, width, height);
        background.setFill(Color.TRANSPARENT);

        Double m = marbles.stream().max(Comparator.<Double>naturalOrder()).orElseGet(() -> 0d);
        Line line = new Line(0, 0, Math.max(width - 2 * padding, m), 0);

        line.setStrokeType(StrokeType.CENTERED);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);

        line.setTranslateX(padding);
        line.setTranslateY(padding);

        List<Polygon> pentagons = marbles.stream().map((x) -> {
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

    public static Node observable(double width, double height, List<Double> marbles, double ghost) {
        Group observable = (Group) observable(width,height,marbles);

        double padding = height/2;

        Polygon ghostPentagon = NGon.pointUp(0, padding, 5, 0.8 * padding);

        ghostPentagon.setStrokeType(StrokeType.INSIDE);
        ghostPentagon.setStroke(Color.GRAY);
        ghostPentagon.setStrokeWidth(2);
        ghostPentagon.setFill(Color.TRANSPARENT);

        ghostPentagon.setTranslateX(ghost);

        observable.getChildren().add(ghostPentagon);

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
