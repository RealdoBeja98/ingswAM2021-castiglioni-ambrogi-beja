package it.polimi.ingsw.Table.Decks.Development;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Decks.Card;
import it.polimi.ingsw.Table.Decks.Production;
import it.polimi.ingsw.Table.Decks.ResourceProduction;


public class DevelopmentCard extends Card implements Production {

    private Resource[] cost;
    private int[] costNumber;
    private Type type;
    private int level;
    private Resource[] requirements;
    private int[] costRequirements;
    private Resource[] products;
    private int[] costProducts;
    private int victoryPoints;

    public DevelopmentCard(Resource[] cost, int[] costNumber, Type type, int level,
                           Resource[] requirements, int[] costRequirements, Resource[] products, int[] costProducts, int victoryPoints){
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

    public int getLevel() {
        return level;
    }

    public Resource[] getCost() {
        return cost;
    }

    public Type getType() {
        return type;
    }

    public Resource[] getRequirements() {
        return requirements;
    }

    public Resource[] getProducts() {
        return products;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public int[] getCostNumber() {
        return costNumber;
    }

    public int[] getCostRequirements() {
        return costRequirements;
    }

    public int[] getCostProducts() {
        return costProducts;
    }

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
