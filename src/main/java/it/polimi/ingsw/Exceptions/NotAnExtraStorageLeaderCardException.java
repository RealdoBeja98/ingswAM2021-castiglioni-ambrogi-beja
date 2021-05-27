package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class NotAnExtraStorageLeaderCardException extends Exception{

    /**
     * Constructor of the class
     */
    public NotAnExtraStorageLeaderCardException(){
        super("This isn't an ExtraStorageLeaderCard!");
    }
}
