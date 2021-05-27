package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class NoDevelopmentCardInThisPositionException extends Exception{

    /**
     * Constructor of the class
     */
    public NoDevelopmentCardInThisPositionException(){
        super("There isn't any development card in this position!");
    }
}
