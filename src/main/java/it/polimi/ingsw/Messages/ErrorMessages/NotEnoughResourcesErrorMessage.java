package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NotEnoughResourcesErrorMessage extends ErrorMessage {

    public NotEnoughResourcesErrorMessage(){
        identifier = "ERROR_NOT_ENOUGH_RESOURCES";
    }

    @Override
    public String toString(){
        return "Error: you aren't able to buy this DevelopmentCard!";
    }

}
