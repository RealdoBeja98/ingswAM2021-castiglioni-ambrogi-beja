package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.DifferentResourceAlreadyPresentErrorMessage;

/**
 * Class of a created exception
 */
public class DifferentResourceInThisShelfException extends MessageException {

    /**
     * Constructor of the class
     */
    public DifferentResourceInThisShelfException(){
        super("There is another resource type in this shelf");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return DifferentResourceAlreadyPresentErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new DifferentResourceAlreadyPresentErrorMessage();
    }

}
