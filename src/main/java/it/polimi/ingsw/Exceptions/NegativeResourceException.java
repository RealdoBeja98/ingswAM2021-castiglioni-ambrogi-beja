package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class NegativeResourceException extends Exception{

    /**
     * Constructor of the class
     */
    public NegativeResourceException(){
        super("Cannot subtract anymore resource!");
    }

}
