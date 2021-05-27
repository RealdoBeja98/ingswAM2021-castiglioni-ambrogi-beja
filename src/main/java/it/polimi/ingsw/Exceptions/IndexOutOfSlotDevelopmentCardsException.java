package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class IndexOutOfSlotDevelopmentCardsException extends Exception{

    /**
     * Constructor of the class
     */
    public IndexOutOfSlotDevelopmentCardsException(){
        super("You are out of bound of slots for development cards!");
    }
}
