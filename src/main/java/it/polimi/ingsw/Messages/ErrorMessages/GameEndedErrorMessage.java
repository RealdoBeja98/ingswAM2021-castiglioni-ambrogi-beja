package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class GameEndedErrorMessage extends ErrorMessage {

    public GameEndedErrorMessage(){
        identifier = "ERROR_GAME_ENDED";
    }

    @Override
    public String toString(){
        return "Error: the game is ended!";
    }

}
