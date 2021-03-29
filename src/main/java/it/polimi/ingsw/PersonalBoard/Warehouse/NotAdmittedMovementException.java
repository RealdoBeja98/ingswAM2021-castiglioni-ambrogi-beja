package it.polimi.ingsw.PersonalBoard.Warehouse;

public class NotAdmittedMovementException extends Exception {

    public NotAdmittedMovementException(){
        super("Not admitted movement!");
    }

}
