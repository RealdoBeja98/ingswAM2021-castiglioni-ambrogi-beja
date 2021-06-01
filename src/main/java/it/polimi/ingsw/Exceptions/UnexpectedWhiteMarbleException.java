package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.WhiteMarbleErrorMessage;

/**
 * Class of a created exception
 */
public class UnexpectedWhiteMarbleException extends MessageException {

    /**
     * Constructor of the class
     */
    public UnexpectedWhiteMarbleException(){
        super("Unexpected white marble!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return WhiteMarbleErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new WhiteMarbleErrorMessage();
    }

}
