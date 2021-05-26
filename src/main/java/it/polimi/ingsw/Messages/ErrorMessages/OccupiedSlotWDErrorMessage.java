package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class OccupiedSlotWDErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public OccupiedSlotWDErrorMessage(){
        identifier = "ERROR_OCCUPIED_SLOT_WD";
    }

    @Override
    public String toString(){
        return "Error: this position is already taken!";
    }

}
