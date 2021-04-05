package it.polimi.ingsw.Exceptions;

public class PositionInvalidException extends Exception {

    public PositionInvalidException(){
        super("This position is not valid!");
    }

    @Override
    public String toString() {
        return getMessage();
    }

}
