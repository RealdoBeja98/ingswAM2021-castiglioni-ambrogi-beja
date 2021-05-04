package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NoSelectedPowersErrorMessage extends ErrorMessage {

    public NoSelectedPowersErrorMessage(){
        identifier = "ERROR_NO_SELECTED_POWERS";
    }

    @Override
    public String toString() {
        return "Error: you haven't selected any production power!";
    }

}
