package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NoWhiteMarbleErrorMessage extends ErrorMessage {

    public NoWhiteMarbleErrorMessage(){
        identifier = "ERROR_NO_WHITE_MARBLE";
    }

    @Override
    public String toString(){
        return "Error: you aren't going to change a white marble or you haven't selected a WhiteMarbleLeaderCard!";
    }

}
