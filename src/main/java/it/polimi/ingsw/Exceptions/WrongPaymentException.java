package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class WrongPaymentException extends Exception{

    /**
     * Constructor of the class
     */
    public WrongPaymentException(){
        super("You ar paying with a wrong resource (or you have selected an not-existing slot of WarehouseDepot)!");
    }
}
