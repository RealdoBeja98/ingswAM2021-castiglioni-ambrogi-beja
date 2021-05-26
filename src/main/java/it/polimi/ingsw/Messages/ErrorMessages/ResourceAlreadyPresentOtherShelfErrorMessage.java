package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class ResourceAlreadyPresentOtherShelfErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public ResourceAlreadyPresentOtherShelfErrorMessage(){
        identifier = "ERROR_RESOURCE_ALREADY_PRESENT_OTHER_SHELF";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: this resource is already present in another shelf!";
    }

}
