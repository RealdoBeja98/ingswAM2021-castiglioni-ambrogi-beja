package it.polimi.ingsw.Exceptions;

public class DrawedFromEmptyDeckException extends Exception{
    public DrawedFromEmptyDeckException(){
        super("You drawn from an empty deck!");
    }
}
