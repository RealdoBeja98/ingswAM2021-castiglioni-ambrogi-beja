package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NotStrongboxErrorMessage extends ErrorMessage {

    public NotStrongboxErrorMessage(){
        identifier = "ERROR_NOT_STRONGBOX";
    }

    @Override
    public String toString(){
        return "Error: this isn't a resource addable to strongbox!";
    }

}
