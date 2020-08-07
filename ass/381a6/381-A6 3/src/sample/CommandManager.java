
package commanddemo;

import java.util.Stack;

public class CommandManager {

    private Stack<Command> undoStack;
    private Stack<Command> redoStack;

    // For macro recording:
    boolean recording;
    CompositeCommand cc;
    
    public CommandManager() {
        undoStack = new Stack<Command>();
        redoStack = new Stack<Command>();
    }

    public void performCommand(Command c) {
        if (recording) {
            // Add a copy of the command being performed
            // (the copying is important, because command objects have state)
            cc.addCommand(c.copy());
        }
        c.execute();
        undoStack.push(c);
        redoStack.clear();
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
    
    public void startRecording() {
        cc = new CompositeCommand();
        recording = true;
    }
    
    public void stopRecording() {
        recording = false;
    }
    
    public void playbackRecording() {
        if (cc != null) { // if there is a recorded macro
            performCommand(cc.copy());
        }
    }
    
}
