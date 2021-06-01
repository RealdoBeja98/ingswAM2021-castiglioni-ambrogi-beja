package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoWhiteMarbleErrorMessage;

/**
 * Class of a created exception
 */
public class NoWhiteMarbleException extends MessageException {

    /**
     * Constructor of the class
     */
    public NoWhiteMarbleException(){
        super("This isn't a white marble!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return NoWhiteMarbleErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new NoWhiteMarbleErrorMessage();
    }

}
