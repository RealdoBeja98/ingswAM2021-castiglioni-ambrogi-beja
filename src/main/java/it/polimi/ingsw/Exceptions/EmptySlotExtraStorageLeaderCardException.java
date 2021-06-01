package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.EmptySlotEsErrorMessage;

/**
 * Class of a created exception
 */
public class EmptySlotExtraStorageLeaderCardException extends MessageException {

    /**
     * Constructor of the class
     */
    public EmptySlotExtraStorageLeaderCardException(){
        super("None to be removed!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return EmptySlotEsErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new EmptySlotEsErrorMessage();
    }

}
