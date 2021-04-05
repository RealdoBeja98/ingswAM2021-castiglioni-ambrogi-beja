package it.polimi.ingsw.Exceptions;

public class PositionAlreadyOccupiedException extends Exception {

    public PositionAlreadyOccupiedException(){
        super("This position is already taken!");
    }

}
