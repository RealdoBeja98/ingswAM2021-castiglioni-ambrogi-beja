package it.polimi.ingsw.Exceptions;

public class NotAResourceForStrongBoxException extends Exception{
    public NotAResourceForStrongBoxException(){
        super("This isn't a resource addable to strongbox!");
    }
}
