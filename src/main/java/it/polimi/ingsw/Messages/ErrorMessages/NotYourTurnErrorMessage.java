package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NotYourTurnErrorMessage extends ErrorMessage {

    public NotYourTurnErrorMessage(){
        identifier = "ERROR_NOT_YOUR_TURN";
    }

    @Override
    public String toString(){
        return "Error: now it isn't your turn!";
    }

}
