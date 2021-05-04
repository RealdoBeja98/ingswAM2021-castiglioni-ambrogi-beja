package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NameTakenErrorMessage extends ErrorMessage {

    public NameTakenErrorMessage(){
        identifier = "ERROR_NAME_TAKEN";
    }

    @Override
    public String toString() {
        return "Error: name already taken, please chose a different one!";
    }

}
