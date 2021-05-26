package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class EmptySlotEsErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public EmptySlotEsErrorMessage(){
        identifier = "ERROR_EMPTY_SLOT_ES";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: you have selected an ExtraStorageLeaderCard with empty slots!";
    }

}
