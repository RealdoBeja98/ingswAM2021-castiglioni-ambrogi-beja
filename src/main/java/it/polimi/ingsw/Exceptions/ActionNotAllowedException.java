package it.polimi.ingsw.Exceptions;

public class ActionNotAllowedException extends Exception{
    public ActionNotAllowedException(){
        super("Action is not allowed for the moment!");
    }
}
