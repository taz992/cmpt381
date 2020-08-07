/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commanddemo;

public class MoveUpCommand extends Command {

    Blob blob;
    int dy;
    double cyStateBeforeMove; // state before command is performed
    
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
        // Reverse the operation, OR, restore saved state
        blob.cy = cyStateBeforeMove;
    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public Command copy() {
        return new MoveUpCommand(blob, dy);
    }
    
}
