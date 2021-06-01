package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoCardsPlayErrorMessage;

/**
 * Class of a created exception
 */
public class NoLeaderCardToPlayException extends MessageException{

    /**
     * Constructor of the class
     */
    public NoLeaderCardToPlayException(){
        super("You haven't any leader card playable!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return NoCardsPlayErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new NoCardsPlayErrorMessage();
    }

}
