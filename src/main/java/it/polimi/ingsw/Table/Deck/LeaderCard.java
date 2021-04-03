package it.polimi.ingsw.Table.Deck;

import it.polimi.ingsw.Resource;

public abstract class LeaderCard extends Card{
    protected int victoryPoints;

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public abstract Resource getStorageType();

    public abstract void addResource() throws OccupiedSlotExtraStorageLeaderCardException;
}
