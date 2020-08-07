package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();

        primaryStage.setTitle("Hello World!");

        //add button object
        Button myButton = new Button("Say 'Hello world'");

        //add label object
        Label myLabel = new Label("Default Message");


        VBox root = new VBox();

        root.getChildren().addAll(myButton,myLabel);

        myButton.setOnAction(e -> myLabel.setText("Hello 381!"));


        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }

    public static void main (String[]args){
        launch(args);
    }
}


