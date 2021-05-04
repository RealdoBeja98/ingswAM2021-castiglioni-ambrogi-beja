package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class EmptyDeckErrorMessage extends ErrorMessage {

    public EmptyDeckErrorMessage(){
        identifier = "ERROR_EMPTY_DECK";
    }

    @Override
    public String toString(){
        return "Error: this deck is empty!";
    }

}
