package it.polimi.ingsw.PersonalBoard;

public class NegativeResourceException extends Exception{
    public NegativeResourceException(){
        super("Cannot subtract anymore resource!");
    }
}
