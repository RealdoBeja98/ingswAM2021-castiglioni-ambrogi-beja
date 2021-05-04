package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class AlreadyDiscardedPositionErrorMessage extends ErrorMessage {

    public AlreadyDiscardedPositionErrorMessage(){
        identifier = "ERROR_ALREADY_DISCARDED_POSITION";
    }

    @Override
    public String toString(){
        return "Error: you've already discarded a card in this position!";
    }

}
