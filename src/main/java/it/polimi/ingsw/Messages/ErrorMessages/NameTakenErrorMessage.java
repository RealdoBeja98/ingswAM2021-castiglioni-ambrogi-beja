package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NameTakenErrorMessage extends ErrorMessage {
    @Override
    public String toString() {
        return "ERROR_NAME_TAKEN";
    }
}
