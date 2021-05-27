package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class NullEnumException extends Exception{

    /**
     * Constructor of the class
     */
    public NullEnumException(){
        super("You passed a null pointer instead of an enum");
    }
}
