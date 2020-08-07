
package commanddemo;

public abstract class Command {
    
    public String name;
    
    public abstract void execute();
    public abstract void undo();
    public abstract void redo();
    
    public abstract Command copy();
}
