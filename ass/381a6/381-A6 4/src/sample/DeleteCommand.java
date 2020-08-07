package sample;

public class DeleteCommand implements TextCommand {
    TextModel model;
    int startPos;
    int endPos;
    String str;
    
    public DeleteCommand(TextModel tModel, int start, int end) {
        model = tModel;
        startPos = start;
        endPos = end;
        str = model.getText(startPos, endPos);
    }
    
    public void doIt() {
        model.deleteText(startPos, endPos);
    }
    
    public void undo() {
        model.insertText(startPos, str);
    }
    
    public String toString() {
        return "DeleteCommand: delete " + str + " from " + startPos + " to " + endPos +
                " (insert " + str + " at position " + startPos + ")";
    }
}
