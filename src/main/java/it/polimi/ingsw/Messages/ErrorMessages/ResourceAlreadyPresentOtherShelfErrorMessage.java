package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class ResourceAlreadyPresentOtherShelfErrorMessage extends ErrorMessage {

    public ResourceAlreadyPresentOtherShelfErrorMessage(){
        identifier = "ERROR_RESOURCE_ALREADY_PRESENT_OTHER_SHELF";
    }

    @Override
    public String toString(){
        return "Error: this resource is already present in another shelf!";
    }

}
