package it.polimi.ingsw.Exceptions;

public class WrongPaymentException extends Exception{
    public WrongPaymentException(){
        super("You ar paying with a wrong resource (or you have selected an not-existing slot of WarehouseDepot)!");
    }
}
