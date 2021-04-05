package it.polimi.ingsw.Exceptions;

public class OccupiedSlotExtraStorageLeaderCardException extends Exception {
    public OccupiedSlotExtraStorageLeaderCardException(){
        super("This leader card cannot accept anymore resources!");
    }
}
