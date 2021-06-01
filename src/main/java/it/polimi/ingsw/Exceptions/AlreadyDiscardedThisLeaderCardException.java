package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.AlreadyDiscardedPositionErrorMessage;

/**
 * Class of a created exception
 */
public class AlreadyDiscardedThisLeaderCardException extends MessageException{

    /**
     * Constructor of the class
     */
    public AlreadyDiscardedThisLeaderCardException(){
        super("You can't discard from this position again!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return AlreadyDiscardedPositionErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new AlreadyDiscardedPositionErrorMessage();
    }

}
