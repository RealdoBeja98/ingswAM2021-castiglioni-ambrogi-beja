package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class NoLeaderCardToDiscardException extends Exception{

    /**
     * Constructor of the class
     */
    public NoLeaderCardToDiscardException(){
        super("No leader card in hand to discard!");
    }
}
