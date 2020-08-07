/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commanddemo;

public class MoveRightCommand extends Command {

    Blob blob;
    int dx;
    double cxStateBeforeMove; // state before command is performed
    
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
        // Reverse the operation, OR, restore saved state
        blob.cx = cxStateBeforeMove;
    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public Command copy() {
        return new MoveRightCommand(blob, dx);
    }
    
}
