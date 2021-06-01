package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.RequirementsErrorMessage;

/**
 * Class of a created exception
 */
public class NotSatisfiedRequirementsForThisLeaderCardException extends MessageException {

    /**
     * Constructor of the class
     */
    public NotSatisfiedRequirementsForThisLeaderCardException(){
        super("Not satisfied requirements for this leader card!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return RequirementsErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new RequirementsErrorMessage();
    }

}
