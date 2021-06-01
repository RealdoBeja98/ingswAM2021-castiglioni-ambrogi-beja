package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidEnumErrorMessage;

/**
 * Class of a created exception
 */
public class NullEnumException extends MessageException{

    /**
     * Constructor of the class
     */
    public NullEnumException(){
        super("You passed a null pointer instead of an enum");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return InvalidEnumErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new InvalidEnumErrorMessage();
    }

}
