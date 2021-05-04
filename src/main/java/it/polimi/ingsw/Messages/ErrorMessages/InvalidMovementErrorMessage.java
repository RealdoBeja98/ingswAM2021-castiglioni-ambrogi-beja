package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class InvalidMovementErrorMessage extends ErrorMessage {

    public InvalidMovementErrorMessage(){
        identifier = "ERROR_INVALID_MOVEMENT";
    }

    @Override
    public String toString() {
        return "Error: this movement is invalid!";
    }

}
