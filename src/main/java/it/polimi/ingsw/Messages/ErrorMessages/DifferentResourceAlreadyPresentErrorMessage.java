package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class DifferentResourceAlreadyPresentErrorMessage extends ErrorMessage {

    /**
     * constructor of class
     */
    public DifferentResourceAlreadyPresentErrorMessage(){
        identifier = "ERROR_DIFFERENT_RESOURCE_ALREADY_PRESENT";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: a different resource is already present in this shelf!";
    }

}
