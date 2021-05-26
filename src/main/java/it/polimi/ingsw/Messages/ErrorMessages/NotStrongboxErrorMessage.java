package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NotStrongboxErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public NotStrongboxErrorMessage(){
        identifier = "ERROR_NOT_STRONGBOX";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: this isn't a resource addable to strongbox!";
    }

}
