package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NoDevelopmentCardErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public NoDevelopmentCardErrorMessage(){
        identifier = "ERROR_NO_DEVELOPMENT_CARD";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString() {
        return "Error: you haven't any development card in this position!";
    }

}
