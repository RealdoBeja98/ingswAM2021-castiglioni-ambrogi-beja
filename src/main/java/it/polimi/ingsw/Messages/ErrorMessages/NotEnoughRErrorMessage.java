package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NotEnoughRErrorMessage extends ErrorMessage {

    /**
     * constructor of class
     */
    public NotEnoughRErrorMessage(){
        identifier = "ERROR_NOT_ENOUGH_R";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString() {
        return "Error: you haven't enough resources!";
    }

}
