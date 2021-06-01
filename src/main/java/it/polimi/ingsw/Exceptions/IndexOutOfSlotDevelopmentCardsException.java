package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidPositionErrorMessage;

/**
 * Class of a created exception
 */
public class IndexOutOfSlotDevelopmentCardsException extends MessageException{

    /**
     * Constructor of the class
     */
    public IndexOutOfSlotDevelopmentCardsException(){
        super("You are out of bound of slots for development cards!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return InvalidPositionErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new InvalidPositionErrorMessage();
    }

}
