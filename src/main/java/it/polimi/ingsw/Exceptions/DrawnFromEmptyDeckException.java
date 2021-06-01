package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.EmptyDeckErrorMessage;

/**
 * Class of a created exception
 */
public class DrawnFromEmptyDeckException extends MessageException{

    /**
     * Constructor of the class
     */
    public DrawnFromEmptyDeckException(){
        super("You drawn from an empty deck!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return EmptyDeckErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new EmptyDeckErrorMessage();
    }

}
