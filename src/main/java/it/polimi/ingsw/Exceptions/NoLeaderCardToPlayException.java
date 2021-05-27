package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class NoLeaderCardToPlayException extends Exception{

    /**
     * Constructor of the class
     */
    public NoLeaderCardToPlayException(){
        super("You haven't any leader card playable!");
    }
}
