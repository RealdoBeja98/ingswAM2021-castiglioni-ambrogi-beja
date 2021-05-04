package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class OccupiedSlotWDErrorMessage extends ErrorMessage {

    public OccupiedSlotWDErrorMessage(){
        identifier = "ERROR_OCCUPIED_SLOT_WD";
    }

    @Override
    public String toString(){
        return "Error: this position is already taken!";
    }

}
