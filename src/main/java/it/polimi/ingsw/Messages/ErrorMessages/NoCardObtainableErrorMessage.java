package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NoCardObtainableErrorMessage extends ErrorMessage {

    public NoCardObtainableErrorMessage(){
        identifier = "ERROR_NO_CARD_OBTAINABLE";
    }

    @Override
    public String toString() {
        return "Error: you haven't any development card to obtain!";
    }

}
