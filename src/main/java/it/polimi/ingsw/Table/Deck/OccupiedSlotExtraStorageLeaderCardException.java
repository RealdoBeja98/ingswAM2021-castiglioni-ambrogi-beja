package it.polimi.ingsw.Table.Deck;

public class OccupiedSlotExtraStorageLeaderCardException extends Exception {
    public OccupiedSlotExtraStorageLeaderCardException(){
        super("Resources already placed!");
    }
}
