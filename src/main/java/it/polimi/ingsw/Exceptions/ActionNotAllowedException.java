package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class ActionNotAllowedException extends Exception{

    /**
     * Constructor of the class
     */
    public ActionNotAllowedException(){
        super("Action is not allowed for the moment!");
    }
}
