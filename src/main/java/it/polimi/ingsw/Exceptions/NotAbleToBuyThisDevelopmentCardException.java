package it.polimi.ingsw.Exceptions;

public class NotAbleToBuyThisDevelopmentCardException extends Exception{
    public NotAbleToBuyThisDevelopmentCardException(){
        super("You aren't able to buy this development card!");
    }
}
