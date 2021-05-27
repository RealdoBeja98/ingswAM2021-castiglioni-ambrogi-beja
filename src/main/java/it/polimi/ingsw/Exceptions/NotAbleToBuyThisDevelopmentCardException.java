package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class NotAbleToBuyThisDevelopmentCardException extends Exception{

    /**
     * Constructor of the class
     */
    public NotAbleToBuyThisDevelopmentCardException(){
        super("You aren't able to buy this development card!");
    }
}
