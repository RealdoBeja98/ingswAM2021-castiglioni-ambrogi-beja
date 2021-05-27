package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class PlayerDoesNotExistsException extends Exception{

    /**
     * Constructor of the class
     */
    public PlayerDoesNotExistsException(){
        super("Player does not exists!");
    }
}
