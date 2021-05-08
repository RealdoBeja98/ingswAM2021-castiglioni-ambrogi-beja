package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Enums.Resource;
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
     */
    public ProductionPowerLeaderCard(int victoryPoints, Type costOfLeaderCard, Resource requirement) {
        this.whatIAm = LeaderCardType.PRODUCTIONPOWER;
        this.victoryPoints = victoryPoints;
        this.costOfLeaderCard = costOfLeaderCard;
        this.requirement = requirement;
    }

    /**
     * Getter of the parameter costOfLeaderCard
     *
     * @return the cost of the card, of type Type
     */
    public Type getCostOfLeaderCard() {
        return costOfLeaderCard;
    }

    /**
     * This method creates a new instance of the class resource production, given the requirement specified in this card and the static output
     *
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
     * This method is to export the LeaderCard to a String
     *
     * @return the String exported
     */
    @Override
    public String export() {
        String result = "P";
        String fromVictoryPoints = String.valueOf(victoryPoints);
        String fromCostOfLeaderCard = "";
        switch (costOfLeaderCard) {
            case GREEN:
                fromCostOfLeaderCard = "g";
                break;
            case BLUE:
                fromCostOfLeaderCard = "b";
                break;
            case YELLOW:
                fromCostOfLeaderCard = "y";
                break;
            case PURPLE:
                fromCostOfLeaderCard = "p";
                break;
            default:
                break;
        }
        String fromRequirement = "";
        switch (requirement) {
            case FAITH:
                fromRequirement = "0";
                break;
            case COIN:
                fromRequirement = "1";
                break;
            case STONE:
                fromRequirement = "2";
                break;
            case SERVANT:
                fromRequirement = "3";
                break;
            case SHIELD:
                fromRequirement = "4";
                break;
            case WHITE:
                fromRequirement = "5";
                break;
        }
        result = new StringBuilder().append(result).append(fromVictoryPoints).append(fromCostOfLeaderCard).append(fromRequirement).toString();
        return result;
    }

    /**
     * Constructor method of this class from the exported string
     */
    public ProductionPowerLeaderCard(String importedString) {
        this.whatIAm = LeaderCardType.PRODUCTIONPOWER;
        victoryPoints = Integer.parseInt(importedString.substring(1, 2));
        String toAddCostOfLeaderCard = importedString.substring(2, 3);
        if(toAddCostOfLeaderCard.equals("g")){
            costOfLeaderCard = Type.GREEN;
        } else if(toAddCostOfLeaderCard.equals("b")){
            costOfLeaderCard = Type.BLUE;
        } else if(toAddCostOfLeaderCard.equals("y")){
            costOfLeaderCard = Type.YELLOW;
        } else {
            costOfLeaderCard = Type.PURPLE;
        }
        String toAddRequirement = importedString.substring(3, 4);
        if(toAddRequirement.equals("0")){
            requirement = Resource.FAITH;
        } else if(toAddRequirement.equals("1")){
            requirement = Resource.COIN;
        } else if(toAddRequirement.equals("2")){
            requirement = Resource.STONE;
        } else if(toAddRequirement.equals("3")){
            requirement = Resource.SERVANT;
        } else if(toAddRequirement.equals("4")){
            requirement = Resource.SHIELD;
        } else {
            requirement = Resource.WHITE;
        }
    }

}
