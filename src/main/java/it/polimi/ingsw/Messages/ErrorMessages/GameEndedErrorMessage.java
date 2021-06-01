package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class GameEndedErrorMessage extends ErrorMessage {

    /**
     * constructor of class
     */
    public GameEndedErrorMessage(){
        identifier = "ERROR_GAME_ENDED";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: the game is ended!";
    }

}
