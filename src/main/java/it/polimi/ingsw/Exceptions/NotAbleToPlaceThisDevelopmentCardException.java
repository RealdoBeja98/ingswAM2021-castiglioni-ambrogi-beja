package it.polimi.ingsw.Exceptions;

public class NotAbleToPlaceThisDevelopmentCardException extends Exception{
    public NotAbleToPlaceThisDevelopmentCardException(){
        super("You aren't able to place this development card in your slots for development cards!");
    }
}
