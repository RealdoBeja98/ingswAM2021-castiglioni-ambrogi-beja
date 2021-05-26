package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class OccupiedSlotLCErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public OccupiedSlotLCErrorMessage(){
        identifier = "ERROR_OCCUPIED_SLOT_LC";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: all slots of this ExtraStorageLeaderCard are occupied!";
    }

}
