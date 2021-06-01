package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoDevelopmentCardErrorMessage;

/**
 * Class of a created exception
 */
public class NoDevelopmentCardInThisPositionException extends MessageException{

    /**
     * Constructor of the class
     */
    public NoDevelopmentCardInThisPositionException(){
        super("There isn't any development card in this position!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return NoDevelopmentCardErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new NoDevelopmentCardErrorMessage();
    }

}
