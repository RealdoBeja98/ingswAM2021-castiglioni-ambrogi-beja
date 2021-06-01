package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoResourcePErrorMessage;

/**
 * Class of a created exception
 */
public class NoResourceToPayException extends MessageException{

    /**
     * Constructor of the class
     */
    public NoResourceToPayException(){
        super("No resource to pay exception!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return NoResourcePErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new NoResourcePErrorMessage();
    }

}
