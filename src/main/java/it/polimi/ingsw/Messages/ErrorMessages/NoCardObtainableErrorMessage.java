package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NoCardObtainableErrorMessage extends ErrorMessage {

    /**
     * constructor of class
     */
    public NoCardObtainableErrorMessage(){
        identifier = "ERROR_NO_CARD_OBTAINABLE";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString() {
        return "Error: you haven't any development card to obtain!";
    }

}
