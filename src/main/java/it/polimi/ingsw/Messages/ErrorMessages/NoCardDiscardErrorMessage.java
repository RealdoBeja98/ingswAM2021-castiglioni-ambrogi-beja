package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NoCardDiscardErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public NoCardDiscardErrorMessage(){
        identifier = "ERROR_NO_CARDS_DISCARD";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: you haven't any leader card to discard!";
    }

}
