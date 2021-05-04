package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class InvalidSelectionErrorMessage extends ErrorMessage {

    public InvalidSelectionErrorMessage(){
        identifier = "ERROR_INVALID_SELECTION";
    }

    @Override
    public String toString(){
        return "Error: this selection is invalid!";
    }

}
