package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    TextArea editor;
    TextModel model;

    ArrayList<TextCommand> history;
    int currentCommandIndex;

    @Override
    public void start(Stage primaryStage) {
        model = new TextModel();
        history = new ArrayList<>();
        currentCommandIndex = 0;

        editor = new TextArea();
        editor.setFont(Font.font(36));
        editor.setOnKeyPressed(this::handlePressed);
        editor.setOnKeyTyped(this::handleTyped);

        Button undo = new Button("Undo!");
        undo.setFont(Font.font(24));
        Button redo = new Button("Redo!");
        redo.setFont(Font.font(24));

        undo.setOnAction(this::handleUndo);
        redo.setOnAction(this::handle_redo);

        VBox root = new VBox();
        root.getChildren().addAll(editor, undo, redo);

        Scene scene = new Scene(root, 500, 500);

        primaryStage.setTitle("Backwards Undo Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handlePressed(KeyEvent event) {
        System.out.println("Key pressed: " + event.getCode());
        if (event.getCode() == KeyCode.BACK_SPACE) {
            DeleteCommand cmd = new DeleteCommand(model, model.caretPosition-1,
                    model.caretPosition);
//                    editor.getCaretPosition()-1,
            //                  editor.getCaretPosition()-1);
            history.add(cmd);
            System.out.println(cmd.toString());
            currentCommandIndex++;
            cmd.doIt();
        }
        if (event.getCode() == KeyCode.DELETE) {
            DeleteCommand cmd = new DeleteCommand(model,
                    editor.getCaretPosition(),
                    editor.getCaretPosition());
            history.add(cmd);
            System.out.println(cmd.toString());
            currentCommandIndex++;
            cmd.doIt();
        }
        if (event.getCode() == KeyCode.LEFT) {
            model.moveCaret(-1);
        }
        if (event.getCode() == KeyCode.RIGHT) {
            model.moveCaret(1);
        }
        editor.setText(model.text);
        editor.positionCaret(model.caretPosition);
        event.consume();
    }

    public void handleTyped(KeyEvent event) {
        System.out.println("Key typed: " + event.getCharacter());
        if (event.getCharacter().compareTo("a") >= 0 &&
                event.getCharacter().compareTo("z") <= 0) {
            InsertCommand cmd = new InsertCommand(model,
                    model.caretPosition,
                    event.getCharacter());
            history.add(cmd);
            currentCommandIndex++;
            System.out.println(cmd.toString());
            cmd.doIt();
            editor.setText(model.text);
            editor.positionCaret(model.caretPosition);
        }
        event.consume();
    }

    public void handleUndo(ActionEvent event) {
        System.out.println("Undo!");
        if (currentCommandIndex > 0) {
            TextCommand cmd = history.get(currentCommandIndex - 1);
            System.out.println(cmd.toString());
            currentCommandIndex--;
            cmd.undo();
        } else {
            System.out.println("No possible undos");
        }
        editor.setText(model.text);
        editor.positionCaret(model.caretPosition);
        editor.requestFocus();
        event.consume();
    }

    public void handle_redo(ActionEvent event) {
        System.out.println("Redo!");
        if (currentCommandIndex < history.size()) {
            TextCommand cmd = history.get(currentCommandIndex);
            System.out.println(cmd.toString());
            currentCommandIndex++;
            cmd.doIt();
        } else {
            System.out.println("No possible redos");
        }
        editor.setText(model.text);
        editor.positionCaret(model.caretPosition);
        editor.requestFocus();
        event.consume();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
