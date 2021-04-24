package it.polimi.ingsw.Exceptions;

public class EmptySlotYetException extends Exception {
    public EmptySlotYetException(){
        super("This slot was yet empty!");
    }
}
