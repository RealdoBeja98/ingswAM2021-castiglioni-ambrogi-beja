package it.polimi.ingsw.Messages.ErrorMessages;
import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class GameDontExistErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public GameDontExistErrorMessage(){
        identifier = "ERROR_GAME_DONT_EXIST";
    }

    @Override
    public String toString() {
        return "Error: Game don't exist, please create a new one with -n!";
    }

}