package it.polimi.ingsw.Exceptions;

public class NoLeaderCardToDiscardException extends Exception{
    public NoLeaderCardToDiscardException(){
        super("No leader card in hand to discard!");
    }
}
