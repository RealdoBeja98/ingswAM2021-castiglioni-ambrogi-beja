package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class SelectedADevelopmentCardYetException extends Exception {

    /**
     * Constructor of the class
     */
    public SelectedADevelopmentCardYetException(){
        super("You can't select another development card");
    }
}
