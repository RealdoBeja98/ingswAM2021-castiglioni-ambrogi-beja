package it.polimi.ingsw.Exceptions;


public class NameAlreadyRegisteredException extends Exception{
    public NameAlreadyRegisteredException(){
        super("This name is already registered!");
    }
}
