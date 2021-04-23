package it.polimi.ingsw.Exceptions;

public class AlreadySelectedADevelopmentCardException extends Exception {
    public AlreadySelectedADevelopmentCardException(){
        super("You can't select another development card");
    }
}
