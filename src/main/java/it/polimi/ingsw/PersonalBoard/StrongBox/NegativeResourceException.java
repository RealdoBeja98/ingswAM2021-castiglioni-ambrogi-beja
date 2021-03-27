package it.polimi.ingsw.PersonalBoard.StrongBox;

public class NegativeResourceException extends Exception{
    public NegativeResourceException(){
        super("Cannot subtract anymore resource!");
    }
}
