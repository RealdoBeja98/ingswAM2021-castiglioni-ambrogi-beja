package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class GameAlreadyStartedException extends Exception {

    /**
     * Constructor of the class
     */
    public GameAlreadyStartedException(){
        super(" This game is already started");
    }

}
