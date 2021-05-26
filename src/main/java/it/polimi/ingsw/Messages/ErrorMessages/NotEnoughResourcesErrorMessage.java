package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NotEnoughResourcesErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public NotEnoughResourcesErrorMessage(){
        identifier = "ERROR_NOT_ENOUGH_RESOURCES";
    }

    @Override
    public String toString(){
        return "Error: you aren't able to buy this DevelopmentCard!";
    }

}
