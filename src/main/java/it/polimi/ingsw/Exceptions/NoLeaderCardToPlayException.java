package it.polimi.ingsw.Exceptions;

public class NoLeaderCardToPlayException extends Exception{
    public NoLeaderCardToPlayException(){
        super("You haven't any leader card playable!");
    }
}
