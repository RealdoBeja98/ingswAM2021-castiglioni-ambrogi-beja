package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class AlreadyDiscardedPositionErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public AlreadyDiscardedPositionErrorMessage(){
        identifier = "ERROR_ALREADY_DISCARDED_POSITION";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: you've already discarded a card in this position!";
    }

}
