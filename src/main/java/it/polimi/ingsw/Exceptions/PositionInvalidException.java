package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class PositionInvalidException extends Exception {

    /**
     * Constructor of the class
     */
    public PositionInvalidException(){
        super("This position is not valid!");
    }

    /**
     * This method returns the message to other class
     * @return
     */
    @Override
    public String toString() {
        return getMessage();
    }

}
