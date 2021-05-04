package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class RequirementsErrorMessage extends ErrorMessage {

    public RequirementsErrorMessage(){
        identifier = "ERROR_REQUIREMENTS";
    }

    @Override
    public String toString(){
        return "Error: you haven't satisfied requirements for this LeaderCard!";
    }

}
