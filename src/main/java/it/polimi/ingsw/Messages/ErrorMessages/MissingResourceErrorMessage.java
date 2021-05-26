package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class MissingResourceErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public MissingResourceErrorMessage(){
        identifier = "ERROR_MISSING_RESOURCE";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: you haven't enough resource to pay (using the strongbox)!";
    }

}
