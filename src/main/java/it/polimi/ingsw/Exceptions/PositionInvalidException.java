package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidPositionErrorMessage;

/**
 * Class of a created exception
 */
public class PositionInvalidException extends MessageException {

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

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return InvalidPositionErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new InvalidPositionErrorMessage();
    }

}
