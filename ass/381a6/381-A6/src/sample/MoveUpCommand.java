package sample;

public abstract class MoveUpCommand extends Command{
    Blob blob;
    int dy;
    double cyStateBeforeMove;

    public MoveUpCommand(Blob blob, int dy) {
        this.name = "Move Up";
        this.blob = blob;
        this.dy = dy;
    }

    @Override
    public void execute() {
        this.cyStateBeforeMove = blob.cy;
        blob.cy -= dy;
    }

    @Override
    public void undo() {
        blob.cy = cyStateBeforeMove;
    }

    @Override
    public void redo() {
        blob.cy -= dy;
    }
}
