package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class NoProductionLeaderCardException extends Exception {

    /**
     * Constructor of the class
     */
    public NoProductionLeaderCardException(){
        super("Expected ProductionLeaderCard but not selected it");
    }
}
