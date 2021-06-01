package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class FaithMarbleErrorMessage extends ErrorMessage {

    /**
     * constructor of class
     */
    public FaithMarbleErrorMessage(){
        identifier = "ERROR_FAITH_MARBLE";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: unexpected faith marble!";
    }

}
