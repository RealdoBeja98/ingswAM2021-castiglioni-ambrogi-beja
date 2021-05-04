package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class FaithMarbleErrorMessage extends ErrorMessage {

    public FaithMarbleErrorMessage(){
        identifier = "ERROR_FAITH_MARBLE";
    }

    @Override
    public String toString(){
        return "Error: unexpected faith marble!";
    }

}
