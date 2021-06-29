package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Utilities.ImportExport;
import it.polimi.ingsw.Table.Decks.Production;
import it.polimi.ingsw.Table.Decks.ResourceProduction;

/**
 * This Class represents the leader card that has a production power
 */
public class ProductionPowerLeaderCard extends LeaderCard implements Production {

    private final Type costOfLeaderCard;
    private final Resource requirement;

    /**
     * Constructor method of this class
     *@param costOfLeaderCard cost of leader card
     *@param victoryPoints victory points
     *@param requirement  requirement
     */
    public ProductionPowerLeaderCard(int victoryPoints, Type costOfLeaderCard, Resource requirement) {
        this.whatIAm = LeaderCardType.PRODUCTIONPOWER;
        this.victoryPoints = victoryPoints;
        this.costOfLeaderCard = costOfLeaderCard;
        this.requirement = requirement;
    }

    /**
     * Getter of the parameter requirement
     * @return the requirement of the card production, of type Resource
     */
    public Resource getRequirement(){
        return requirement;
    }

    /**
     * Getter of the parameter costOfLeaderCard
     * @return the cost of the card, of type Type
     */
    public Type getCostOfLeaderCard() {
        return costOfLeaderCard;
    }

    /**
     * This method creates a new instance of the class resource production, given the requirement specified in this card and the static output
     * @return a class that represent the production power of this card, of type ResourceProduction
     */
    public ResourceProduction resourceProduction() {
        int costCoin = 0;
        int costServant = 0;
        int costShield = 0;
        int costStone = 0;
        switch (requirement) {
            case COIN:
                costCoin = 1;
                break;
            case SERVANT:
                costServant = 1;
                break;
            case SHIELD:
                costShield = 1;
                break;
            case STONE:
                costStone = 1;
                break;
            default:
                break;
        }
        return new ResourceProduction(costCoin, costServant, costShield, costStone, 0, 0,
                0, 0, 0, 1, 1);
    }

    /**
     * This method exports the ProductionPowerLeaderCard to a String
     * @return a string with all the card data, of type String
     */
    @Override
    public String export() {
        String result = "P";
        String fromVictoryPoints = String.valueOf(victoryPoints);
        String fromCostOfLeaderCard = ImportExport.exportType(costOfLeaderCard);
        String fromRequirement = ImportExport.exportResource(requirement);
        result = new StringBuilder().append(result).append(fromVictoryPoints).append(fromCostOfLeaderCard).append(fromRequirement).toString();
        return result;
    }

    /**
     * Constructor method of this class from the exported string
     * @param importedString: the string to import
     */
    public ProductionPowerLeaderCard(String importedString) {
        this.whatIAm = LeaderCardType.PRODUCTIONPOWER;
        victoryPoints = Integer.parseInt(importedString.substring(1, 2));
        String toAddCostOfLeaderCard = importedString.substring(2, 3);
        costOfLeaderCard = ImportExport.importType(toAddCostOfLeaderCard);
        String toAddRequirement = importedString.substring(3, 4);
        requirement = ImportExport.importResource(toAddRequirement);
    }

}
