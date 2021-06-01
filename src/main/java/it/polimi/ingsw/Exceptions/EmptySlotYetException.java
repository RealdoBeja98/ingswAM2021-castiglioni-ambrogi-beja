package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.AlreadyEmptyErrorMessage;

/**
 * Class of a created exception
 */
public class EmptySlotYetException extends MessageException {

    /**
     * Constructor of the class
     */
    public EmptySlotYetException(){
        super("This slot was yet empty!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return AlreadyEmptyErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new AlreadyEmptyErrorMessage();
    }

}
