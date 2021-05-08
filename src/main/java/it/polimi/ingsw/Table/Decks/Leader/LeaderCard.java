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
     * This method is to export the LeaderCard to a String
     * @return the String exported
     */
    public abstract String export();

    /**
     * This method is to import and create a new LeaderCard from a String
     * @param importedString the string to import
     * @return the LeaderCard imported
     */
    public LeaderCard importLeaderCard(String importedString){
        String leaderCardType = importedString.substring(0, 1);
        if(leaderCardType.equals("D")){
            return new DiscountLeaderCard(importedString);
        } else if(leaderCardType.equals("E")){
            return new ExtraStorageLeaderCard(importedString);
        } else if(leaderCardType.equals("P")){
            return new ProductionPowerLeaderCard(importedString);
        } else if(leaderCardType.equals("W")){
            return new WhiteMarbleLeaderCard(importedString);
        }
        return null;
    }

}
