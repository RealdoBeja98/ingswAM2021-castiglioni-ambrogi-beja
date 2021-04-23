package it.polimi.ingsw.Exceptions;

public class AlreadyEmptySlotException extends Exception {
    public AlreadyEmptySlotException(){
        super("This slot was yet empty!");
    }
}
