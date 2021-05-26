package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class RequirementsErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public RequirementsErrorMessage(){
        identifier = "ERROR_REQUIREMENTS";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: you haven't satisfied requirements for this LeaderCard!";
    }

}
