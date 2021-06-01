package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoWhiteMarbleErrorMessage;

/**
 * Class of a created exception
 */
public class NoWhiteMarbleLeaderCardException extends MessageException{

    /**
     * Constructor of the class
     */
    public NoWhiteMarbleLeaderCardException(){
        super("There is no white marble leader card!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return NoWhiteMarbleErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new NoWhiteMarbleErrorMessage();
    }

}
