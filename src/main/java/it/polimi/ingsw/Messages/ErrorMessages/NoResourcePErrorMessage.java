package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NoResourcePErrorMessage extends ErrorMessage {

    /**
     * constructor of class
     */
    public NoResourcePErrorMessage(){
        identifier = "ERROR_NO_RESOURCE_P";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: you haven't any resource to pay!";
    }

}
