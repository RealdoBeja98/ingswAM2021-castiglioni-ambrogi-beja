package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class InvalidEnumErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public InvalidEnumErrorMessage(){
        identifier = "ERROR_INVALID_ENUM";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: you passed a null pointer instead of an enum!";
    }

}
