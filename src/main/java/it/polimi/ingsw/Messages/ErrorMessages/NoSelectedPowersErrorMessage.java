package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NoSelectedPowersErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public NoSelectedPowersErrorMessage(){
        identifier = "ERROR_NO_SELECTED_POWERS";
    }

    @Override
    public String toString() {
        return "Error: you haven't selected any production power!";
    }

}
