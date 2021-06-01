package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.MissingResourceErrorMessage;

/**
 * Class of a created exception
 */
public class NegativeResourceException extends MessageException{

    /**
     * Constructor of the class
     */
    public NegativeResourceException(){
        super("Cannot subtract anymore resource!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return MissingResourceErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new MissingResourceErrorMessage();
    }

}
