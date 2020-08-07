package sample;
import java.util.Stack;
import javafx.scene.web.HTMLEditorSkin.Command;

public class CommandManager {
    private Stack<Command> undoStack;
    private Stack<Command> redoStack;

    public CommandManager() {
        undoStack = new Stack<Command>();
        redoStack = new Stack<Command>();
    }

    public void performCommand(Command c) {
        c.execute();
        undoStack.push(c);
        redoStack.clear();
        System.out.println("Performed the " + c.name + " command."); // Logging example
    }

    public void undo() {
        if (undoStack.empty()) {
            System.out.println("Nothing more to undo!");
            return;
        }
        Command c = undoStack.pop();
        c.undo();
        redoStack.push(c);
    }

    public void redo() {
        if (redoStack.empty()) {
            System.out.println("Nothing more to redo!");
            return;
        }
        Command c = redoStack.pop();
        c.undo();
        undoStack.push(c);
    }
}

