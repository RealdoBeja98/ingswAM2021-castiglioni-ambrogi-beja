package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class NotEnoughResourcesException extends Exception{

    /**
     * Constructor of the class
     */
    public NotEnoughResourcesException(){
        super("You don't have enough resources for this production!");
    }
}
