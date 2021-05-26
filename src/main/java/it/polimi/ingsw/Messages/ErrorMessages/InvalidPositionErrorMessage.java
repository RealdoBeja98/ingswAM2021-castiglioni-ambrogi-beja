package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class InvalidPositionErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public InvalidPositionErrorMessage(){
        identifier = "ERROR_INVALID_POSITION";
    }

    @Override
    public String toString(){
        return "Error: this position is invalid!";
    }

}
