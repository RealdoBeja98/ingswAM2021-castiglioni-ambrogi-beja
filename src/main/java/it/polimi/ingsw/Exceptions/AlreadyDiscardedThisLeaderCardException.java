package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class AlreadyDiscardedThisLeaderCardException extends Exception{

    /**
     * Constructor of the class
     */
    public AlreadyDiscardedThisLeaderCardException(){
        super("You can't discard from this position again!");
    }
}
