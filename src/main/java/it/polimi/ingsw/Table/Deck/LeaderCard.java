package it.polimi.ingsw.Table.Deck;

import it.polimi.ingsw.Exceptions.OccupiedSlotExtraStorageLeaderCardException;
import it.polimi.ingsw.Enums.Resource;

public abstract class LeaderCard extends Card{
    protected int victoryPoints;

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public abstract Resource getStorageType();

    public abstract void addResource() throws OccupiedSlotExtraStorageLeaderCardException;
}
