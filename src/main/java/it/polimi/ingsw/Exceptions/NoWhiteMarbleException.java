package it.polimi.ingsw.Exceptions;

public class NoWhiteMarbleException extends Exception {
    public NoWhiteMarbleException(){
        super("This isn't a white marble!");
    }
}
