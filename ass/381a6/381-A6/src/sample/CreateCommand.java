package sample;

public abstract class CreateCommand {
    public String name;

    public abstract  void execute();
    public abstract  void undo();
    public abstract  void redo();

}
