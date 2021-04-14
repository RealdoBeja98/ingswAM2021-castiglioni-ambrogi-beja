package it.polimi.ingsw.Exceptions;

public class YetDiscardedThisLeaderCardException extends Exception{
    public YetDiscardedThisLeaderCardException(){
        super("You can't discard this card again!");
    }
}
