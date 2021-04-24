package it.polimi.ingsw.Exceptions;

public class SelectedADevelopmentCardYetException extends Exception {
    public SelectedADevelopmentCardYetException(){
        super("You can't select another development card");
    }
}
