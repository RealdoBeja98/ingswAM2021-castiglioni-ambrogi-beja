package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class MissingResourceErrorMessage extends ErrorMessage {

    public MissingResourceErrorMessage(){
        identifier = "ERROR_MISSING_RESOURCE";
    }

    @Override
    public String toString(){
        return "Error: you haven't enough resource to pay (using the strongbox)!";
    }

}
