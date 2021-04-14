package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Exceptions.OccupiedSlotExtraStorageLeaderCardException;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Table.Decks.Card;

/**
 * This Class is an abstraction of a leader card
 */
public abstract class LeaderCard extends Card {//<--FIXME import son method as abstract-->

    protected int victoryPoints;
    protected LeaderCardType whatIAm;

    public LeaderCardType getWhatIAm(){ return whatIAm; }

    public int getVictoryPoints(){ return victoryPoints; }

    /**
     *abstract method: see implementation in son classes for the details
     */
    public abstract Resource getStorageType();

    /**
     *abstract method: see implementation in son classes for the details
     */
    public abstract void addResource() throws OccupiedSlotExtraStorageLeaderCardException;

    /**
     *abstract method: see implementation in son classes for the details
     */
    public abstract int occupiedResources();
}
