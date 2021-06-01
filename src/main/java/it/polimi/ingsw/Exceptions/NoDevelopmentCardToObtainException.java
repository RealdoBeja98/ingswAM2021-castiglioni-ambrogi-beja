package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoCardObtainableErrorMessage;

/**
 * Class of a created exception
 */
public class NoDevelopmentCardToObtainException extends MessageException{

    /**
     * Constructor of the class
     */
    public NoDevelopmentCardToObtainException(){
        super("You haven't any development card to obtain!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return NoCardObtainableErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new NoCardObtainableErrorMessage();
    }

}
