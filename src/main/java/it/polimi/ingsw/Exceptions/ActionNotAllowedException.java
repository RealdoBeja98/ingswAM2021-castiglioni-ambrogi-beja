package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;

/**
 * Class of a created exception
 */
public class ActionNotAllowedException extends MessageException{

    /**
     * Constructor of the class
     */
    public ActionNotAllowedException(){
        super("Action is not allowed for the moment!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return InvalidActionErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new InvalidActionErrorMessage();
    }

}
