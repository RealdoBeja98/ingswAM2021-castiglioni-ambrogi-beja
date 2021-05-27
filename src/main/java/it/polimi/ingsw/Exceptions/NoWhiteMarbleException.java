package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class NoWhiteMarbleException extends Exception {

    /**
     * Constructor of the class
     */
    public NoWhiteMarbleException(){
        super("This isn't a white marble!");
    }
}
