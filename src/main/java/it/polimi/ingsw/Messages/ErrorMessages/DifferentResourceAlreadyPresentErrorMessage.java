package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class DifferentResourceAlreadyPresentErrorMessage extends ErrorMessage {

    public DifferentResourceAlreadyPresentErrorMessage(){
        identifier = "ERROR_DIFFERENT_RESOURCE_ALREADY_PRESENT";
    }

    @Override
    public String toString(){
        return "Error: a different resource is already present in this shelf!";
    }

}
