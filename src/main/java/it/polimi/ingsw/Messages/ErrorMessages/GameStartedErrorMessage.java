package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class GameStartedErrorMessage extends ErrorMessage {

    public GameStartedErrorMessage(){
        identifier = "ERROR_GAME_STARTED";
    }

    @Override
    public String toString() {
        return "Error: the game is yet started!";
    }

}
