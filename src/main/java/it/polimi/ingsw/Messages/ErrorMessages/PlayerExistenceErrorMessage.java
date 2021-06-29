package it.polimi.ingsw.Messages.ErrorMessages;
import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * class of player existence errar message
 */
public class PlayerExistenceErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public PlayerExistenceErrorMessage(){
        identifier = "ERROR_PLAYER_EXISTENCE";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString() {
        return "Error: Player doesn't exist or didn't disconnect!";
    }
}
