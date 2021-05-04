package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class WrongResourceErrorMessage extends ErrorMessage {

    public WrongResourceErrorMessage(){
        identifier = "ERROR_WRONG_RESOURCE";
    }

    @Override
    public String toString(){
        return "Error: you are paying with a wrong resource!";
    }

}
