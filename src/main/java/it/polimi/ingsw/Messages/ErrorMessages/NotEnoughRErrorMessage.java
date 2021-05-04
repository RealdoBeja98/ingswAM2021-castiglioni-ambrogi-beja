package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NotEnoughRErrorMessage extends ErrorMessage {

    public NotEnoughRErrorMessage(){
        identifier = "ERROR_NOT_ENOUGH_R";
    }

    @Override
    public String toString() {
        return "Error: you haven't enough resources!";
    }

}
