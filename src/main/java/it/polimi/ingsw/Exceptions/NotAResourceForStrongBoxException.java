package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotStrongboxErrorMessage;

/**
 * Class of a created exception
 */
public class NotAResourceForStrongBoxException extends MessageException{

    /**
     * Constructor of the class
     */
    public NotAResourceForStrongBoxException(){
        super("This isn't a resource addable to strongbox!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return NotStrongboxErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new NotStrongboxErrorMessage();
    }

}
