package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class AlreadyEmptyErrorMessage extends ErrorMessage {

    public AlreadyEmptyErrorMessage(){
        identifier = "ERROR_ALREADY_EMPTY";
    }

    @Override
    public String toString(){
        return "Error: this slot is already empty!";
    }

}
