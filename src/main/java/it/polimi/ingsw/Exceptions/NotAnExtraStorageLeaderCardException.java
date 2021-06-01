package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotEsErrorMessage;

/**
 * Class of a created exception
 */
public class NotAnExtraStorageLeaderCardException extends MessageException{

    /**
     * Constructor of the class
     */
    public NotAnExtraStorageLeaderCardException(){
        super("This isn't an ExtraStorageLeaderCard!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return NotEsErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new NotEsErrorMessage();
    }

}
