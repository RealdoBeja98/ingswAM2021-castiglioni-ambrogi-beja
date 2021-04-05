package it.polimi.ingsw.Exceptions;

public class NotEnoughResourcesException extends Exception{
    public NotEnoughResourcesException(){
        super("You don't have enough resources for this production!");
    }
}
