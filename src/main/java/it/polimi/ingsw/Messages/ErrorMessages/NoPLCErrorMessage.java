package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NoPLCErrorMessage extends ErrorMessage {

    /**
     * constructor of class
     */
    public NoPLCErrorMessage(){
        identifier = "ERROR_NO_PLC";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString() {
        return "Error: You haven't selected a ProductionLeaderCard!";
    }

}
