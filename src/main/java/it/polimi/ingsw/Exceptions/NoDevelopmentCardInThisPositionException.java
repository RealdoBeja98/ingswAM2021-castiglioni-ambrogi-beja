package it.polimi.ingsw.Exceptions;

public class NoDevelopmentCardInThisPositionException extends Exception{
    public NoDevelopmentCardInThisPositionException(){
        super("There isn't any development card in this position!");
    }
}
