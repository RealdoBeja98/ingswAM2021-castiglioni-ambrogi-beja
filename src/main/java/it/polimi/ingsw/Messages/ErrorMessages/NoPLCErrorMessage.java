package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class NoPLCErrorMessage extends ErrorMessage {

    public NoPLCErrorMessage(){
        identifier = "ERROR_NO_PLC";
    }

    @Override
    public String toString() {
        return "Error: You haven't selected a ProductionLeaderCard!";
    }

}
