package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NotEsErrorMessage extends ErrorMessage {

    public NotEsErrorMessage(){
        identifier = "ERROR_NOT_ES";
    }

    @Override
    public String toString(){
        return "Error: this isn't an ExtraStorageLeaderCard!";
    }

}
