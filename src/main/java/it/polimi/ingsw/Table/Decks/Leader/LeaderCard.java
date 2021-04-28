package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Table.Decks.Card;

/**
 * This Class is an abstraction of a leader card
 */
public abstract class LeaderCard extends Card {

    protected int victoryPoints;
    protected LeaderCardType whatIAm;

    /**
     * Getter of the parameter whatIAm
     * @return the type of the card, of type LeaderCardType
     */
    public LeaderCardType getWhatIAm(){ return whatIAm; }

    /**
     * Getter of the parameter victoryPoints
     * @return the number of victory points of the card, of type int
     */
    public int getVictoryPoints(){ return victoryPoints; }

}
