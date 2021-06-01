package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.WrongResourceErrorMessage;

/**
 * Class of a created exception
 */
public class WrongPaymentException extends MessageException{

    /**
     * Constructor of the class
     */
    public WrongPaymentException(){
        super("You ar paying with a wrong resource (or you have selected an not-existing slot of WarehouseDepot)!");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return WrongResourceErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new WrongResourceErrorMessage();
    }

}
