package it.polimi.ingsw.Table.Decks.Leader;

import it.polimi.ingsw.Exceptions.OccupiedSlotExtraStorageLeaderCardException;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Table.Decks.Card;

public abstract class LeaderCard extends Card {
    protected int victoryPoints;

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public abstract Resource getStorageType();

    public abstract void addResource() throws OccupiedSlotExtraStorageLeaderCardException;
}
