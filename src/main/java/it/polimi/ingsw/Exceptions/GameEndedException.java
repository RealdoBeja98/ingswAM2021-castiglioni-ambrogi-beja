package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameEndedErrorMessage;

/**
 * Class of a created exception
 */
public class GameEndedException extends MessageException{

    /**
     * Constructor of the class
     */
    public GameEndedException(){
        super("The game is ended! It's time to show final points!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return GameEndedErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new GameEndedErrorMessage();
    }

}
