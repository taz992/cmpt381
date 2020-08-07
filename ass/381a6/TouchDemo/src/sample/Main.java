package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.TouchPoint;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    //ArrayList<Point2D> fingerOne = new ArrayList<>();
    //ArrayList<Point2D> fingerTwo = new ArrayList<>();
    //HashMap<Integer, TouchPoint> touches = new HashMap<>();
    TouchView view;
    ArrayList<Color> touchColors = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        VBox vert = new VBox();
        view = new TouchView(1000, 800);
        view.setOnTouchPressed(this::handleTouchPressed);
        view.setOnTouchMoved(this::handleTouchMoved);
        view.setOnTouchReleased(this::handleTouchReleased);
        Button clear = new Button("Clear");
        clear.setOnMouseClicked(e -> view.clear());

        touchColors.add(Color.RED);
        touchColors.add(Color.GREEN);
        touchColors.add(Color.BLUE);

        vert.getChildren().addAll(view,clear);
        primaryStage.setScene(new Scene(vert));
        primaryStage.show();
    }

    public void handleTouchPressed(TouchEvent event) {
        for (TouchPoint tp : event.getTouchPoints()) {
            view.drawPressed(tp);
        }
    }

    public void handleTouchMoved(TouchEvent event) {
        for (TouchPoint tp : event.getTouchPoints()) {
            view.drawMoved(tp);
        }
    }

    public void handleTouchReleased(TouchEvent event) {
        for (TouchPoint tp : event.getTouchPoints()) {
            view.drawReleased(tp);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public class TouchView extends Pane {
        Canvas myCanvas;
        GraphicsContext gc;
        double width, height;

        public TouchView(int newW, int newH) {
            width = newW;
            height = newH;
            setStyle("-fx-background-color: mediumseagreen");
            myCanvas = new Canvas(width, height);
            gc = myCanvas.getGraphicsContext2D();
            getChildren().add(myCanvas);
        }

        public void layoutChildren() {
            width = this.getWidth();
            height = this.getHeight();
            myCanvas.setWidth(width);
            myCanvas.setHeight(height);
        }

        private void setColor(int id) {
            if (id <= 3) {
                gc.setFill(touchColors.get(id - 1));
            } else {
                gc.setFill(Color.FLORALWHITE);
            }
        }

        public void clear() {
            gc.clearRect(0,0,1000,800);
        }

        public void drawPressed(TouchPoint tp) {
            setColor(tp.getId());
            gc.fillRect(tp.getX() - 10, tp.getY() - 10, 20, 20);
        }

        public void drawMoved(TouchPoint tp) {
            setColor(tp.getId());
            gc.fillOval(tp.getX() - 5, tp.getY() - 5, 10, 10);
        }

        public void drawReleased(TouchPoint tp) {
            setColor(tp.getId());
            gc.fillRect(tp.getX() - 10, tp.getY() - 10, 20, 20);
        }
    }
}

