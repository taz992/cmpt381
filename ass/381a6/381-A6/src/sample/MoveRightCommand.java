package sample;

public abstract class MoveRightCommand extends Command {
    Blob blob;
    int dx;
    double cxStateBeforeMove;

    public MoveRightCommand(Blob blob, int dx) {
        this.name = "Move Right";
        this.blob = blob;
        this.dx = dx;
    }

    @Override
    public void execute() {
        this.cxStateBeforeMove = blob.cx;
        blob.cx += dx;
    }

    @Override
    public void undo() {

        blob.cx = cxStateBeforeMove;
    }

    @Override
    public void redo() {
        blob.cx += dx;
    }
}
