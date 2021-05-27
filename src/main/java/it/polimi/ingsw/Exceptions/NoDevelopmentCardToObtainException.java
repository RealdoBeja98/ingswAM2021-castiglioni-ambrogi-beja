package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class NoDevelopmentCardToObtainException extends Exception{

    /**
     * Constructor of the class
     */
    public NoDevelopmentCardToObtainException(){
        super("You haven't any development card to obtain!");
    }
}
