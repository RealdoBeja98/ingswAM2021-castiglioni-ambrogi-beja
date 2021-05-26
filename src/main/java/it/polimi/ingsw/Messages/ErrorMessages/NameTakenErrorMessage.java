package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NameTakenErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public NameTakenErrorMessage(){
        identifier = "ERROR_NAME_TAKEN";
    }

    @Override
    public String toString() {
        return "Error: name already taken, please chose a different one!";
    }

}
