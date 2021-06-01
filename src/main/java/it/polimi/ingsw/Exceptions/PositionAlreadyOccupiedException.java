package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.OccupiedSlotWDErrorMessage;

/**
 * Class of a created exception
 */
public class PositionAlreadyOccupiedException extends MessageException {

    /**
     * Constructor of the class
     */
    public PositionAlreadyOccupiedException(){
        super("This position is already taken!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return OccupiedSlotWDErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new OccupiedSlotWDErrorMessage();
    }

}
