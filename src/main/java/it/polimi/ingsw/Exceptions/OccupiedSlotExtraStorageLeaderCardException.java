package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.OccupiedSlotLCErrorMessage;

/**
 * Class of a created exception
 */
public class OccupiedSlotExtraStorageLeaderCardException extends MessageException {

    /**
     * Constructor of the class
     */
    public OccupiedSlotExtraStorageLeaderCardException(){
        super("This leader card cannot accept anymore resources!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return OccupiedSlotLCErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new OccupiedSlotLCErrorMessage();
    }

}
