package it.polimi.ingsw.Exceptions;

public class NoDevelopmentCardToObtainException extends Exception{
    public NoDevelopmentCardToObtainException(){
        super("You haven't any development card to obtain!");
    }
}
