package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    Canvas canvas1;
    GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) {
        canvas1 = new Canvas(500, 500);
        gc = canvas1.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillRect(100,100, 200, 200);
        gc.setStroke(Color.BLUEVIOLET);
        gc.setLineWidth(10);
        //gc.fillOval(0,0,500,300);
        gc.strokeOval(0,0,500,300);
        canvas1.setOnMouseDragged(e -> {
            gc.setFill(Color.color(Math.random(), Math.random(), Math.random()));
            gc.fillOval(e.getX() - 20, e.getY() - 20, 40, 40);
        });

        Button clearButton = new Button();
        clearButton.setText("Clear Canvas");
        clearButton.setOnAction(e -> gc.clearRect(0, 0, 500, 500));

        VBox root = new VBox();
        root.getChildren().addAll(canvas1,clearButton);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Canvas Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
