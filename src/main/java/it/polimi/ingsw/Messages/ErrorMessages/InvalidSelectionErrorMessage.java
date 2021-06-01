package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class InvalidSelectionErrorMessage extends ErrorMessage {

    /**
     * constructor of class
     */
    public InvalidSelectionErrorMessage(){
        identifier = "ERROR_INVALID_SELECTION";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: this selection is invalid!";
    }

}
