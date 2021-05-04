package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NoResourcePErrorMessage extends ErrorMessage {

    public NoResourcePErrorMessage(){
        identifier = "ERROR_NO_RESOURCE_P";
    }

    @Override
    public String toString(){
        return "Error: you haven't any resource to pay!";
    }

}
