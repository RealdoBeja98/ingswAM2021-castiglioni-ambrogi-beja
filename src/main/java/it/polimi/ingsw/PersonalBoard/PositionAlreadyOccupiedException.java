package it.polimi.ingsw.PersonalBoard;

public class PositionAlreadyOccupiedException extends Exception {
    public PositionAlreadyOccupiedException(){
        super("This position is already taken!");
    }
}
