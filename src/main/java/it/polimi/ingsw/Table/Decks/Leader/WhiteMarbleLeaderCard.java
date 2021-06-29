package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Utilities.ImportExport;
import it.polimi.ingsw.Table.Market.Marbles.*;
import java.util.ArrayList;

/**
 * This Class represents the leader card that give a discount
 */
public class WhiteMarbleLeaderCard extends LeaderCard {

    private final Type[] costOfLeaderCard;
    private final Marble whiteMarble;

    /**
     * Constructor method of this class
     * @param costOfLeaderCard cost of leader card
     * @param victoryPoints victory points
     * @param whiteMarble white marble
     */
    public WhiteMarbleLeaderCard(int victoryPoints, Type[] costOfLeaderCard, Marble whiteMarble){
        this.whatIAm = LeaderCardType.WHITE;
        this.victoryPoints = victoryPoints;
        this.costOfLeaderCard = costOfLeaderCard;
        this.whiteMarble = whiteMarble;
    }

    /**
     * Getter of the parameter costOfLeaderCard
     * @return a copy of the array that represent the cost of the card, of type Type[]
     */
    public Type[] getCostOfLeaderCard(){
        return costOfLeaderCard.clone();
    }

    /**
     * Getter of the parameter whiteMarble
     * @return the marble that replace a white marble, of type Marble
     */
    public Marble getWhiteMarble(){
        return whiteMarble;
    }

    /**
     * This method exports the WhiteMarbleLeaderCard to a String
     * @return a string with all the card data, of type String
     */
    @Override
    public String export() {
        String result = "W";
        String fromVictoryPoints = String.valueOf(victoryPoints);
        String fromCostOfLeaderCard = "";
        for(Type i : costOfLeaderCard){
            String toAdd = ImportExport.exportType(i);
            fromCostOfLeaderCard = new StringBuilder().append(fromCostOfLeaderCard).append(toAdd).toString();
        }
        String fromWhiteMarble = ImportExport.exportResource(whiteMarble.getWhatIAm());
        result = new StringBuilder().append(result).append(fromVictoryPoints).append(fromCostOfLeaderCard).append(fromWhiteMarble).toString();
        return result;
    }

    /**
     * Constructor method of this class from the exported string
     * @param importedString: the string to import
     */
    public WhiteMarbleLeaderCard(String importedString) {
        this.whatIAm = LeaderCardType.WHITE;
        victoryPoints = Integer.parseInt(importedString.substring(1, 2));
        String toWhiteMarble = importedString.substring(importedString.length() - 1);
        whiteMarble = ImportExport.importMarble(toWhiteMarble);
        String fromCostOfLeaderCard = importedString.substring(2, importedString.length() - 1);
        ArrayList<Type> toCostOfLeaderCard = new ArrayList<>();
        for(int i = 0; i < fromCostOfLeaderCard.length(); i++){
            String toAdd = fromCostOfLeaderCard.substring(i, i+1);
            toCostOfLeaderCard.add(ImportExport.importType(toAdd));
        }
        costOfLeaderCard = new Type[toCostOfLeaderCard.size()];
        toCostOfLeaderCard.toArray(costOfLeaderCard);
    }

}
