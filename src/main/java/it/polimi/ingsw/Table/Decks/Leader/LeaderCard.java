package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Table.Decks.Card;

/**
 * This Class is an abstraction of a leader card
 */
public abstract class LeaderCard extends Card {

    protected int victoryPoints;
    protected LeaderCardType whatIAm;

    public LeaderCardType getWhatIAm(){ return whatIAm; }

    public int getVictoryPoints(){ return victoryPoints; }

}
