package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.AlreadySelectedSomethingErrorMessage;

/**
 * Class of a created exception
 */
public class SelectedADevelopmentCardYetException extends MessageException {

    /**
     * Constructor of the class
     */
    public SelectedADevelopmentCardYetException(){
        super("You can't select another development card");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return AlreadySelectedSomethingErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new AlreadySelectedSomethingErrorMessage();
    }

}
