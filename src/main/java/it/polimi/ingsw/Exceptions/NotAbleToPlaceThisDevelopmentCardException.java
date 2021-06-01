package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidSelectionErrorMessage;

/**
 * Class of a created exception
 */
public class NotAbleToPlaceThisDevelopmentCardException extends MessageException{

    /**
     * Constructor of the class
     */
    public NotAbleToPlaceThisDevelopmentCardException(){
        super("You aren't able to place this development card in your slots for development cards!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return InvalidSelectionErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new InvalidSelectionErrorMessage();
    }

}
