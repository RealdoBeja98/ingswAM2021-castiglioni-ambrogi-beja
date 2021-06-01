package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.FaithMarbleErrorMessage;

/**
 * Class of a created exception
 */
public class UnexpectedFaithMarbleException extends MessageException {

    /**
     * Constructor of the class
     */
    public UnexpectedFaithMarbleException(){
        super("Unexpected faith marble");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return FaithMarbleErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new FaithMarbleErrorMessage();
    }

}
