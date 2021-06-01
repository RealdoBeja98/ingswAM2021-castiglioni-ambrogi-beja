package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotEnoughRErrorMessage;

/**
 * Class of a created exception
 */
public class NotEnoughResourcesException extends MessageException{

    /**
     * Constructor of the class
     */
    public NotEnoughResourcesException(){
        super("You don't have enough resources for this production!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return NotEnoughRErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new NotEnoughRErrorMessage();
    }

}
