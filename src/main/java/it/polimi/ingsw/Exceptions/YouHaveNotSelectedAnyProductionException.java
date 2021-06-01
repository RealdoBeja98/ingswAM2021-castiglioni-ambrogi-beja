package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoSelectedPowersErrorMessage;

/**
 * Class of a created exception
 */
public class YouHaveNotSelectedAnyProductionException extends MessageException{

    /**
     * Constructor of the class
     */
    public YouHaveNotSelectedAnyProductionException(){
        super("You have not selected any production power!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return NoSelectedPowersErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new NoSelectedPowersErrorMessage();
    }

}
