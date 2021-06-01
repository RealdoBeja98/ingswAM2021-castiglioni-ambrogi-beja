package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoPLCErrorMessage;

/**
 * Class of a created exception
 */
public class NoProductionLeaderCardException extends MessageException {

    /**
     * Constructor of the class
     */
    public NoProductionLeaderCardException(){
        super("Expected ProductionLeaderCard but not selected it");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return NoPLCErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new NoPLCErrorMessage();
    }

}
