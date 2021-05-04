package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class InvalidPositionErrorMessage extends ErrorMessage {

    public InvalidPositionErrorMessage(){
        identifier = "ERROR_INVALID_POSITION";
    }

    @Override
    public String toString(){
        return "Error: this position is invalid!";
    }

}
