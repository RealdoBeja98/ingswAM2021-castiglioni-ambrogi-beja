package it.polimi.ingsw.PersonalBoard.Warehouse;

public class PositionAlreadyOccupiedException extends Exception {

    public PositionAlreadyOccupiedException(){
        super("This position is already taken!");
    }

}
