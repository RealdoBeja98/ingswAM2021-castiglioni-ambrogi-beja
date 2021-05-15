package it.polimi.ingsw.Messages.ErrorMessages;
import it.polimi.ingsw.Messages.ErrorMessage;

public class GameDontExistErrorMessage extends ErrorMessage {

    public GameDontExistErrorMessage(){
        identifier = "ERROR_GAME_DONT_EXIST";
    }

    @Override
    public String toString() {
        return "Error: Game don't exist, please create a new one with -n!";
    }

}