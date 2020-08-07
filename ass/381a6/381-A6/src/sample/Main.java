package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class Main extends Application {

    BlobView view;
    BlobController controller;
    BlobModel model;
    InteractionModel iModel;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        // create MVC components
        view = new BlobView(800,800);
        controller = new BlobController();
        model = new BlobModel();
        iModel = new InteractionModel();

        // connect MVC components
        view.setModel(model);
        view.setInteractionModel(iModel);
        controller.setModel(model);
        controller.setInteractionModel(iModel);
        model.addSubscriber(view);
        iModel.addSubscriber(view);

        // set up event handling
        view.setOnMousePressed(controller::handlePressed);
        view.setOnMouseDragged(controller::handleDrag);
        view.setOnMouseReleased(controller::handleRelease);
        view.setOnKeyPressed(controller::handleKeyPressed);
        view.setOnKeyReleased(controller::handleKeyReleased);

        root.getChildren().add(view);
        primaryStage.setTitle("381 A6 Demo");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        view.requestFocus();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
