package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class WhiteMarbleErrorMessage extends ErrorMessage {

    public WhiteMarbleErrorMessage(){
        identifier = "ERROR_WHITE_MARBLE";
    }

    @Override
    public String toString(){
        return "Error: unexpected white marble!";
    }

}
