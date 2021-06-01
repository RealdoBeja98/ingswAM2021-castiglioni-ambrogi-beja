package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.DifferentStorageTypeErrorMessage;

/**
 * Class of a created exception
 */
public class DifferentStorageException extends MessageException{

    /**
     * Constructor of the class
     */
    public DifferentStorageException(){
        super("Different storage type!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return DifferentStorageTypeErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new DifferentStorageTypeErrorMessage();
    }

}
