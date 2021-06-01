package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoCardDiscardErrorMessage;

/**
 * Class of a created exception
 */
public class NoLeaderCardToDiscardException extends MessageException{

    /**
     * Constructor of the class
     */
    public NoLeaderCardToDiscardException(){
        super("No leader card in hand to discard!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return NoCardDiscardErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new NoCardDiscardErrorMessage();
    }

}
