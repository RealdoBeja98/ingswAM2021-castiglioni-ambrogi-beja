package it.polimi.ingsw.Exceptions;

public class PlayerDoesNotExistsException extends Exception{
    public PlayerDoesNotExistsException(){
        super("Player does not exists!");
    }
}
