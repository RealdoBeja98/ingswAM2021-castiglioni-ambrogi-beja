package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidMovementErrorMessage;

/**
 * Class of a created exception
 */
public class NotAdmittedMovementException extends MessageException {

    /**
     * Constructor of the class
     */
    public NotAdmittedMovementException(){
        super("Not admitted movement!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return InvalidMovementErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new InvalidMovementErrorMessage();
    }

}
