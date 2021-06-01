package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NoResourceAErrorMessage extends ErrorMessage {

    /**
     * constructor of class
     */
    public NoResourceAErrorMessage(){
        identifier = "ERROR_NO_RESOURCE_A";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: you haven't any resource to add!";
    }

}
