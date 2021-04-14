package it.polimi.ingsw.Exceptions;

public class DrawnFromEmptyDeckException extends Exception{
    public DrawnFromEmptyDeckException(){
        super("You drawn from an empty deck!");
    }
}
