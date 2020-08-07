package sample;

public class TextModel {
    String text = "";
    int caretPosition = 0;
    
    public void insertText(int start, String str) {
        System.out.println("Model: insertText  start:" + start + "  string: " + str);
        String s1 = text.substring(0, start);
        String s2 = text.substring(start);
        text = s1 + str + s2;
        caretPosition = start + str.length();
    }
    
    public void deleteText(int start, int end) {
        System.out.println("Model: deleteText  start:" + start + " end: " + end);
        if (end > text.length()) {
            text = text.substring(0, start);
        } else {
            text = text.substring(0, start) + text.substring(end);
        }
        caretPosition = start;
    }
    
    public void moveCaret(int val) {
        caretPosition += val;
        if (caretPosition < 0) { caretPosition = 0; }
        if (caretPosition > text.length()) { caretPosition = text.length(); }
    }
    
    public String getText(int start, int end) {
        return text.substring(start, end);
    }
}
