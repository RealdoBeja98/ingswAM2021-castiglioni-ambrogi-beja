package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class InvalidMovementErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public InvalidMovementErrorMessage(){
        identifier = "ERROR_INVALID_MOVEMENT";
    }

    @Override
    public String toString() {
        return "Error: this movement is invalid!";
    }

}
