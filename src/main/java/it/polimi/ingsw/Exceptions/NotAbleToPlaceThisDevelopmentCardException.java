package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class NotAbleToPlaceThisDevelopmentCardException extends Exception{

    /**
     * Constructor of the class
     */
    public NotAbleToPlaceThisDevelopmentCardException(){
        super("You aren't able to place this development card in your slots for development cards!");
    }
}
