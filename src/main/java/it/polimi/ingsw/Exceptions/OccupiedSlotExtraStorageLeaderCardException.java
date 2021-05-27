package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class OccupiedSlotExtraStorageLeaderCardException extends Exception {

    /**
     * Constructor of the class
     */
    public OccupiedSlotExtraStorageLeaderCardException(){
        super("This leader card cannot accept anymore resources!");
    }
}
