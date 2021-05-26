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

    @Override
    public String toString(){
        return "Error: you passed a null pointer instead of an enum!";
    }

}
