package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NoDevelopmentCardErrorMessage extends ErrorMessage {

    public NoDevelopmentCardErrorMessage(){
        identifier = "ERROR_NO_DEVELOPMENT_CARD";
    }

    @Override
    public String toString() {
        return "Error: you haven't any development card in this position!";
    }

}
