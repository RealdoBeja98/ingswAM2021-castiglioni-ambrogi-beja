package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidMovementErrorMessage;

/**
 * Class of a created exception
 */
public class IndexOutOfWarehouseDepotsException extends MessageException{

    /**
     * Constructor of the class
     */
    public IndexOutOfWarehouseDepotsException(){
        super("Index out of bounds of the WarehouseDepots!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return InvalidMovementErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new InvalidMovementErrorMessage();
    }

}
