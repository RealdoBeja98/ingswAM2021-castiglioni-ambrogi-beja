package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class AlreadySelectedSomethingErrorMessage extends ErrorMessage {

    public AlreadySelectedSomethingErrorMessage(){
        identifier = "ERROR_ALREADY_SELECTED_SOMETHING";
    }

    @Override
    public String toString(){
        return "Error: you have yet selected a development card!";
    }

}
