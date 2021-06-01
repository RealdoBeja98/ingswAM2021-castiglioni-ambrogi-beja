package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoResourceAErrorMessage;

/**
 * Class of a created exception
 */
public class NoResourceToAddException extends MessageException {

    /**
     * Constructor of the class
     */
    public NoResourceToAddException(){
        super("No resources to add!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return NoResourceAErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new NoResourceAErrorMessage();
    }

}
