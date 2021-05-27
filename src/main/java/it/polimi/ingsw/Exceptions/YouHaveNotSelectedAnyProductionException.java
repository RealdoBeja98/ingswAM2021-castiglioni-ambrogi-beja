package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class YouHaveNotSelectedAnyProductionException extends Exception{

    /**
     * Constructor of the class
     */
    public YouHaveNotSelectedAnyProductionException(){
        super("You have not selected any production power!");
    }
}
