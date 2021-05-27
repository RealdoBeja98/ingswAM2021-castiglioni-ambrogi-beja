package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class NotAResourceForStrongBoxException extends Exception{

    /**
     * Constructor of the class
     */
    public NotAResourceForStrongBoxException(){
        super("This isn't a resource addable to strongbox!");
    }
}
