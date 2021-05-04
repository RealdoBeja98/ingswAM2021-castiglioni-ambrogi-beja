package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NoCardDiscardErrorMessage extends ErrorMessage {

    public NoCardDiscardErrorMessage(){
        identifier = "ERROR_NO_CARDS_DISCARD";
    }

    @Override
    public String toString(){
        return "Error: you haven't any leader card to discard!";
    }

}
