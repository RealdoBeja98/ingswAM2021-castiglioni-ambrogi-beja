package it.polimi.ingsw.Exceptions;

public class AlreadyDiscardedThisLeaderCardException extends Exception{
    public AlreadyDiscardedThisLeaderCardException(){
        super("You can't discard from this position again!");
    }
}
