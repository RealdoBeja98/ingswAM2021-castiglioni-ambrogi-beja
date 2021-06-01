package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class WrongResourceErrorMessage extends ErrorMessage {

    /**
     * constructor of class
     */
    public WrongResourceErrorMessage(){
        identifier = "ERROR_WRONG_RESOURCE";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: you are paying with a wrong resource!";
    }

}
