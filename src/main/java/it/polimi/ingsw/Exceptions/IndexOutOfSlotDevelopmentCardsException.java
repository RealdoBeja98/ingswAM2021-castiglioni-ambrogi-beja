package it.polimi.ingsw.Exceptions;

public class IndexOutOfSlotDevelopmentCardsException extends Exception{
    public IndexOutOfSlotDevelopmentCardsException(){
        super("You are out of bound of slots for development cards!");
    }
}
