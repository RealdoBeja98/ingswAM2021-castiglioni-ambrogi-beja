package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NotYourTurnErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public NotYourTurnErrorMessage(){
        identifier = "ERROR_NOT_YOUR_TURN";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: now it isn't your turn!";
    }

}
