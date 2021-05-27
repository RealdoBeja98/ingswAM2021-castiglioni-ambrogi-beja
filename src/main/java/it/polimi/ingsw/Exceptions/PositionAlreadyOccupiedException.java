package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class PositionAlreadyOccupiedException extends Exception {

    /**
     * Constructor of the class
     */
    public PositionAlreadyOccupiedException(){
        super("This position is already taken!");
    }

}
