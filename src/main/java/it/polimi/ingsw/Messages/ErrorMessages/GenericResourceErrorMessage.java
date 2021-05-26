package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class GenericResourceErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public GenericResourceErrorMessage(){
        identifier = "ERROR_GENERIC_RESOURCE";
    }

    @Override
    public String toString() {
        return "Error: there isn't any generic resource to obtain!";
    }

}
