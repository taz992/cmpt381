package sample;

public abstract class MoveLeftCommand extends Command {
    Blob blob;
    int dx;
    double cxStateBeforeMove;
    public MoveLeftCommand(Blob blob, int dx) {
        this.name = "Move Left";
        this.blob = blob;
        this.dx = dx;
    }

    @Override
    public void execute() {
        this.cxStateBeforeMove = blob.cx;
        blob.cx -= dx;
    }

    @Override
    public void undo() {
        blob.cx = cxStateBeforeMove;
    }

    @Override
    public void redo() {
        blob.cx -= dx;
    }

}
