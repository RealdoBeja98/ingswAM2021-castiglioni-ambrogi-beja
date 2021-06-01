package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class GameStartedErrorMessage extends ErrorMessage {

    /**
     * constructor of class
     */
    public GameStartedErrorMessage(){
        identifier = "ERROR_GAME_STARTED";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString() {
        return "Error: the game is already started!";
    }

}
