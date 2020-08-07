/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commanddemo;

public abstract class Command {
    
    public String name;
    
    public abstract void execute();
    public abstract void undo();
    public abstract void redo();
    
    public abstract Command copy();
}
