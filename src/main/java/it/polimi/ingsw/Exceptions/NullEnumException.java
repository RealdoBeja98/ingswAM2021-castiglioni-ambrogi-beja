package it.polimi.ingsw.Exceptions;

public class NullEnumException extends Exception{
    public NullEnumException(){
        super("You passed a null pointer instead of an enum");
    }
}
