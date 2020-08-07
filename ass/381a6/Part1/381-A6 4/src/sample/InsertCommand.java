package sample;

public class InsertCommand implements TextCommand {
    TextModel model;
    int startPos;
    String str;
    
    public InsertCommand(TextModel tModel, int start, String insertString) {
        model = tModel;
        startPos = start;
        str = insertString;
    }
    
    public void doIt() {
        model.insertText(startPos, str);
    }
    
    public void undo() {
        model.deleteText(startPos, startPos + str.length());
    }
    
    public String toString() {
        return "InsertCommand: insert " + str + " at " + startPos +
                " (delete from " + startPos + " to " + (startPos + str.length()) + ")";
    }
}
