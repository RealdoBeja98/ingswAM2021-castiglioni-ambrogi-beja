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

    /**
     * This method exports the DevelopmentCard to a String
     * @return a string with all the card data, of type String
     */
    public String export(){
        String result = "";
        for(Resource i: cost){
            String toAdd = "";
            switch (i){
                case FAITH: toAdd = "0";
                    break;
                case COIN: toAdd = "1";
                    break;
                case STONE: toAdd = "2";
                    break;
                case SERVANT: toAdd = "3";
                    break;
                case SHIELD: toAdd = "4";
                    break;
                case WHITE: toAdd = "5";
                    break;
            }
            result = new StringBuilder().append(result).append(toAdd).toString();
        }
        result = new StringBuilder().append(result).append("!").toString();
        for(int i: costNumber){
            String toAdd = String.valueOf(i);
            result = new StringBuilder().append(result).append(toAdd).toString();
        }
        result = new StringBuilder().append(result).append("!").toString();
        switch (type){
            case GREEN: result = new StringBuilder().append(result).append("g").toString();
                break;
            case BLUE: result = new StringBuilder().append(result).append("b").toString();
                break;
            case YELLOW: result = new StringBuilder().append(result).append("y").toString();
                break;
            case PURPLE: result = new StringBuilder().append(result).append("p").toString();
                break;
            default: break;
        }
        result = new StringBuilder().append(result).append("!").toString();
        result = new StringBuilder().append(result).append(String.valueOf(level)).toString();
        result = new StringBuilder().append(result).append("!").toString();
        for(Resource i: requirements){
            String toAdd = "";
            switch (i){
                case FAITH: toAdd = "0";
                    break;
                case COIN: toAdd = "1";
                    break;
                case STONE: toAdd = "2";
                    break;
                case SERVANT: toAdd = "3";
                    break;
                case SHIELD: toAdd = "4";
                    break;
                case WHITE: toAdd = "5";
                    break;
            }
            result = new StringBuilder().append(result).append(toAdd).toString();
        }
        result = new StringBuilder().append(result).append("!").toString();
        for(int i: costRequirements){
            String toAdd = String.valueOf(i);
            result = new StringBuilder().append(result).append(toAdd).toString();
        }
        result = new StringBuilder().append(result).append("!").toString();
        for(Resource i: products){
            String toAdd = "";
            switch (i){
                case FAITH: toAdd = "0";
                    break;
                case COIN: toAdd = "1";
                    break;
                case STONE: toAdd = "2";
                    break;
                case SERVANT: toAdd = "3";
                    break;
                case SHIELD: toAdd = "4";
                    break;
                case WHITE: toAdd = "5";
                    break;
            }
            result = new StringBuilder().append(result).append(toAdd).toString();
        }
        result = new StringBuilder().append(result).append("!").toString();
        for(int i: costProducts){
            String toAdd = String.valueOf(i);
            result = new StringBuilder().append(result).append(toAdd).toString();
        }
        result = new StringBuilder().append(result).append("!").toString();
        result = new StringBuilder().append(result).append(String.valueOf(victoryPoints)).toString();
        return result;
    }

    /**
     * Constructor method of this class from the exported string
     * @param importedString: the string to import
     */
    public DevelopmentCard(String importedString){
        String[] strings = importedString.split("!");
        cost = new Resource[strings[0].length()];
        for(int i = 0; i < strings[0].length(); i++){
            String toAdd = strings[0].substring(i, i+1);
            if(toAdd.equals("0")){
                cost[i] = Resource.FAITH;
            } else if(toAdd.equals("1")){
                cost[i] = Resource.COIN;
            } else if(toAdd.equals("2")){
                cost[i] = Resource.STONE;
            } else if(toAdd.equals("3")){
                cost[i] = Resource.SERVANT;
            } else if(toAdd.equals("4")){
                cost[i] = Resource.SHIELD;
            } else if(toAdd.equals("5")){
                cost[i] = Resource.WHITE;
            }
        }
        costNumber = new int[strings[1].length()];
        for(int i = 0; i < strings[1].length(); i++){
            String toAdd = strings[1].substring(i, i+1);
            costNumber[i] = Integer.parseInt(toAdd);
        }
        if(strings[2].equals("g")){
            type = Type.GREEN;
        } else if(strings[2].equals("b")){
            type = Type.BLUE;
        } else if(strings[2].equals("y")){
            type = Type.YELLOW;
        } else {
            type = Type.PURPLE;
        }
        level = Integer.parseInt(strings[3]);
        requirements = new Resource[strings[4].length()];
        for(int i = 0; i < strings[4].length(); i++){
            String toAdd = strings[4].substring(i, i+1);
            if(toAdd.equals("0")){
                requirements[i] = Resource.FAITH;
            } else if(toAdd.equals("1")){
                requirements[i] = Resource.COIN;
            } else if(toAdd.equals("2")){
                requirements[i] = Resource.STONE;
            } else if(toAdd.equals("3")){
                requirements[i] = Resource.SERVANT;
            } else if(toAdd.equals("4")){
                requirements[i] = Resource.SHIELD;
            } else if(toAdd.equals("5")){
                requirements[i] = Resource.WHITE;
            }
        }
        costRequirements = new int[strings[5].length()];
        for(int i = 0; i < strings[5].length(); i++){
            String toAdd = strings[5].substring(i, i+1);
            costRequirements[i] = Integer.parseInt(toAdd);
        }
        products = new Resource[strings[6].length()];
        for(int i = 0; i < strings[6].length(); i++){
            String toAdd = strings[6].substring(i, i+1);
            if(toAdd.equals("0")){
                products[i] = Resource.FAITH;
            } else if(toAdd.equals("1")){
                products[i] = Resource.COIN;
            } else if(toAdd.equals("2")){
                products[i] = Resource.STONE;
            } else if(toAdd.equals("3")){
                products[i] = Resource.SERVANT;
            } else if(toAdd.equals("4")){
                products[i] = Resource.SHIELD;
            } else if(toAdd.equals("5")){
                products[i] = Resource.WHITE;
            }
        }
        costProducts = new int[strings[7].length()];
        for(int i = 0; i < strings[7].length(); i++){
            String toAdd = strings[7].substring(i, i+1);
            costProducts[i] = Integer.parseInt(toAdd);
        }
        victoryPoints = Integer.parseInt(strings[8]);
    }

}
