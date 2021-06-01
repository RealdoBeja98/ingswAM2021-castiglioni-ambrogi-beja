package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotEnoughResourcesErrorMessage;

/**
 * Class of a created exception
 */
public class NotAbleToBuyThisDevelopmentCardException extends MessageException{

    /**
     * Constructor of the class
     */
    public NotAbleToBuyThisDevelopmentCardException(){
        super("You aren't able to buy this development card!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return NotEnoughResourcesErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new NotEnoughResourcesErrorMessage();
    }

}
