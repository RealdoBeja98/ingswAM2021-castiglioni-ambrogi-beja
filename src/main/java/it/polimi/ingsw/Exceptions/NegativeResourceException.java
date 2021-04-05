package it.polimi.ingsw.Exceptions;

public class NegativeResourceException extends Exception{

    public NegativeResourceException(){
        super("Cannot subtract anymore resource!");
    }

}
