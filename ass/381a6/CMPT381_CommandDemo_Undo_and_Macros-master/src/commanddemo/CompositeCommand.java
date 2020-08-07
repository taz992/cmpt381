/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commanddemo;

import java.util.ArrayList;

public class CompositeCommand extends Command {

    ArrayList<Command> commands;
    
    public CompositeCommand() {
        commands = new ArrayList<Command>();
    }

    public void addCommand(Command c) {
        commands.add(c);
    }
    
    @Override
    public void execute() {
        // perform the recorded sequence of commands
        for (Command c : commands) {
            c.execute();
        }
    }

    @Override
    public void undo() {
        // undo the recorded commands in reverse order
        for (int i = commands.size()-1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }

    @Override
    public void redo() {
        execute();
    }
    
    @Override
    public Command copy() {
        CompositeCommand cc = new CompositeCommand();
        for (Command c : commands) {
            cc.addCommand(c.copy());
        }
        return cc;
    }
}
