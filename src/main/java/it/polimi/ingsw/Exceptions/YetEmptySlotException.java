package it.polimi.ingsw.Exceptions;

public class YetEmptySlotException extends Exception {
    public YetEmptySlotException(){
        super("This slot was yet empty!");
    }
}
