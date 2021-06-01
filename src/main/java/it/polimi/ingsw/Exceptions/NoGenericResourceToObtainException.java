package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GenericResourceErrorMessage;

/**
 * Class of a created exception
 */
public class NoGenericResourceToObtainException extends MessageException{

    /**
     * Constructor of the class
     */
    public NoGenericResourceToObtainException(){
        super("No generic resource to obtain!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return GenericResourceErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new GenericResourceErrorMessage();
    }

}
