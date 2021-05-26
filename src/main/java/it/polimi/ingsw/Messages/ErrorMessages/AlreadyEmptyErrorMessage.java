package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;
/**
 * Class of an error message
 */
public class AlreadyEmptyErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public AlreadyEmptyErrorMessage(){
        identifier = "ERROR_ALREADY_EMPTY";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: this slot is already empty!";
    }

}
