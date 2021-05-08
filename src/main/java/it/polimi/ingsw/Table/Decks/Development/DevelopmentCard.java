package it.polimi.ingsw.Table.Decks.Development;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Decks.Card;
import it.polimi.ingsw.Table.Decks.Production;
import it.polimi.ingsw.Table.Decks.ResourceProduction;

/**
 * This Class represents a development card
 */
public class DevelopmentCard extends Card implements Production {

    private final Resource[] cost;
    private final int[] costNumber;
    private final Type type;
    private final int level;
    private final Resource[] requirements;
    private final int[] costRequirements;
    private final Resource[] products;
    private final int[] costProducts;
    private final int victoryPoints;

    /**
     * Constructor method of this class
     */
    public DevelopmentCard(Resource[] cost, int[] costNumber, Type type, int level,
                           Resource[] requirements, int[] costRequirements, Resource[] products,
                           int[] costProducts, int victoryPoints){
        this.cost = cost;
        this.costNumber = costNumber;
        this.type = type;
        this.level = level;
        this.requirements = requirements;
        this.costRequirements = costRequirements;
        this.products = products;
        this.costProducts = costProducts;
        this.victoryPoints = victoryPoints;
    }

    /**
     * Getter of the parameter level
     * @return the level of the card, of type int
     */
    public int getLevel() {
        return level;
    }

    /**
     * Getter of the parameter cost
     * @return a copy of the type of card's cost, of type Resource[]
     */
    public Resource[] getCost() {
        return cost.clone();
    }

    /**
     * Getter of the parameter costNumber
     * @return a copy of the number of card's cost, of type int[]
     */
    public int[] getCostNumber() {
        return costNumber.clone();
    }

    /**
     * Getter of the parameter requirements
     * @return a copy of the type of card's requirements, of type Resource[]
     */
    public Resource[] getRequirements() {
        return requirements.clone();
    }

    /**
     * Getter of the parameter costRequirements
     * @return a copy of the number of card's requirements, of type int[]
     */
    public int[] getCostRequirements() {
        return costRequirements.clone();
    }

    /**
     * Getter of the parameter products
     * @return a copy of the type of card's products, of type Resource[]
     */
    public Resource[] getProducts() {
        return products.clone();
    }

    /**
     * Getter of the parameter costProducts
     * @return a copy of the number of card's products, of type int[]
     */
    public int[] getCostProducts() {
        return costProducts.clone();
    }

    /**
     * Getter of the parameter type
     * @return the type of the card, of type Type
     */
    public Type getType() {
        return type;
    }

    /**
     * Getter of the parameter victoryPoints
     * @return the victory point of the card, of type int
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * This method creates a new instance of the class resource production, given the requirement specified in this card and the static output
     * @return a class that represent the production power of this card, of type ResourceProduction
     */
    public ResourceProduction resourceProduction(){
        int costCoin = 0;
        int costServant = 0;
        int costShield = 0;
        int costStone = 0;

        for(int i = 0; i < requirements.length; i++){
            switch(requirements[i]){
                case COIN: costCoin += costRequirements[i];
                    break;

                case SERVANT: costServant +=costRequirements[i];
                    break;

                case SHIELD: costShield += costRequirements[i];
                    break;

                case STONE: costStone +=costRequirements[i];
                    break;

                default: break;
            }
        }
        int productionCoin = 0;
        int productionServant = 0;
        int productionShield = 0;
        int productionStone = 0;
        int productionFaith = 0;
        for(int i = 0; i < products.length; i++){
            switch(products[i]){
                case COIN: productionCoin += costRequirements[i];
                    break;

                case SERVANT: productionServant +=costProducts[i];
                    break;

                case SHIELD: productionShield += costProducts[i];
                    break;

                case STONE: productionStone +=costProducts[i];
                    break;

                case FAITH: productionFaith += costProducts[i];
                default: break;
            }
        }

        return new ResourceProduction(costCoin, costServant, costShield, costStone, 0, productionCoin,
                productionServant, productionShield, productionStone, 0, productionFaith);
    }
}
