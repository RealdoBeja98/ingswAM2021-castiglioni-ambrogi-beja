package it.polimi.ingsw.Deck;
import it.polimi.ingsw.PersonalBoard.PersonalBoard;
import it.polimi.ingsw.PersonalBoard.StrongBox.NegativeResourceException;
import it.polimi.ingsw.Resource;
import it.polimi.ingsw.Type;

public class DevelopmentCard extends Card{

    private Resource[] cost = new Resource[3];
    private int[] costNumber = new int[3];
    private Type type;
    private int level;
    private Resource[] requirements = new Resource[2];
    private int[] costRequirements = new int[2];
    private Resource[] products = new Resource[2];
    private int[] costProducts = new int[2];
    private int victoryPoints;

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

    public void resourceProduction(PersonalBoard board){
        for(int i = 0; i < 2; i++){
            try {
                board.getStrongBox().remove(requirements[i], costRequirements[i]);
            } catch (NegativeResourceException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < 2; i++){
            board.getStrongBox().add(products[i], costProducts[i]);
        }
    }

}
