package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class DrawnFromEmptyDeckException extends Exception{

    /**
     * Constructor of the class
     */
    public DrawnFromEmptyDeckException(){
        super("You drawn from an empty deck!");
    }
}
