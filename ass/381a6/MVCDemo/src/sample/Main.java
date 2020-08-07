package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    BlobView view;
    BlobController controller;
    BlobModel model;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        StackPane root = new StackPane();

        view = new BlobView(1000,600);
        controller = new BlobController();
        model = new BlobModel();

        view.setController(controller);
        view.setModel(model);
        controller.setModel(model);
        model.addSubscriber(view);

        root.getChildren().add(view);
        primaryStage.setTitle("MVC Demo");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
