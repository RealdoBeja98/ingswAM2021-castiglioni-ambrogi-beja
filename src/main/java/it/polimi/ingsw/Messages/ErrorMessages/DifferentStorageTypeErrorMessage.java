package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class DifferentStorageTypeErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public DifferentStorageTypeErrorMessage(){
        identifier = "ERROR_DIFFERENT_STORAGE_TYPE";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: you've selected a different storage type!";
    }

}
