package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class EmptySlotYetException extends Exception {

    /**
     * Constructor of the class
     */
    public EmptySlotYetException(){
        super("This slot was yet empty!");
    }
}
