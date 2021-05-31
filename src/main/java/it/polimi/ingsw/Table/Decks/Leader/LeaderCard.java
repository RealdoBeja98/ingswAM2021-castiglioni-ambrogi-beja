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
    public int getVictoryPoints(){
        return victoryPoints;
    }

    /**
     * This method exports the LeaderCard to a String
     * @return a string with all the card data, of type String
     */
    public abstract String export();

    /**
     * This method is to import and create a new LeaderCard from a String
     * @param importedString: the string to import
     * @return the imported card, of type LeaderCard
     */
    public static LeaderCard importLeaderCard(String importedString){
        String leaderCardType = importedString.substring(0, 1);
        switch (leaderCardType){
            case "D": return new DiscountLeaderCard(importedString);
            case "E": return new ExtraStorageLeaderCard(importedString);
            case "P": return new ProductionPowerLeaderCard(importedString);
            case "W": return new WhiteMarbleLeaderCard(importedString);
        }
        return null;
    }

}
