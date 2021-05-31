package it.polimi.ingsw.Table.Decks.Development;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Decks.Card;
import it.polimi.ingsw.Table.Decks.Production;
import it.polimi.ingsw.Table.Decks.ResourceProduction;
import it.polimi.ingsw.Utilities.MyInt;

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
     * this method fill the cost/products for the ResourceProduction
     * @param coin: for the cost/products of the coin
     * @param servant: for the cost/products of the servant
     * @param shield: for the cost/products of the shield
     * @param stone: for the cost/products of the stone
     */
    private void fillCost(MyInt coin, MyInt servant, MyInt shield, MyInt stone, MyInt faith, Resource var[], int varCost[]){
        for(int i = 0; i < var.length; i++){
            switch(var[i]){
                case COIN: coin.n += varCost[i];
                    break;

                case SERVANT: servant.n += varCost[i];
                    break;

                case SHIELD: shield.n += varCost[i];
                    break;

                case STONE: stone.n += varCost[i];
                    break;

                case FAITH: faith.n += varCost[i];
                    break;

                default: break;
            }
        }
    }

    /**
     * This method creates a new instance of the class resource production, given the requirement specified in this card and the static output
     * @return a class that represent the production power of this card, of type ResourceProduction
     */
    public ResourceProduction resourceProduction(){
        MyInt costCoin = new MyInt();
        MyInt costServant = new MyInt();
        MyInt costShield = new MyInt();
        MyInt costStone = new MyInt();
        fillCost(costCoin, costServant, costShield, costStone, null, requirements, costRequirements);

        MyInt productionCoin = new MyInt();
        MyInt productionServant = new MyInt();
        MyInt productionShield = new MyInt();
        MyInt productionStone = new MyInt();
        MyInt productionFaith = new MyInt();
        fillCost(productionCoin, productionServant, productionShield, productionStone, productionFaith, products, costProducts);

        return new ResourceProduction(costCoin.n, costServant.n, costShield.n, costStone.n, 0, productionCoin.n,
                productionServant.n, productionShield.n, productionStone.n, 0, productionFaith.n);
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
        result = new StringBuilder().append(result).append(level).toString();
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
        result = new StringBuilder().append(result).append(victoryPoints).toString();
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
