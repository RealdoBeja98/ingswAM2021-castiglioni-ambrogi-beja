package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NoResourceAErrorMessage extends ErrorMessage {

    public NoResourceAErrorMessage(){
        identifier = "ERROR_NO_RESOURCE_A";
    }

    @Override
    public String toString(){
        return "Error: you haven't any resource to add!";
    }

}
