package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class InvalidActionErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public InvalidActionErrorMessage(){
        identifier = "ERROR_INVALID_ACTION";
    }

    @Override
    public String toString(){
        return "Error: this action is invalid (in this moment)!";
    }

}
