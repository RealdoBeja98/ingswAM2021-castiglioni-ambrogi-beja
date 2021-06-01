package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class EmptyDeckErrorMessage extends ErrorMessage {

    /**
     * constructor of class
     */
    public EmptyDeckErrorMessage(){
        identifier = "ERROR_EMPTY_DECK";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: this deck is empty!";
    }

}
