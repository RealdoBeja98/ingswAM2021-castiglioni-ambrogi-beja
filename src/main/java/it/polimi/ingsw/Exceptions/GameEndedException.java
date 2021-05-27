package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class GameEndedException extends Exception{

    /**
     * Constructor of the class
     */
    public GameEndedException(){
        super("The game is ended! It's time to show final points!");
    }
}
