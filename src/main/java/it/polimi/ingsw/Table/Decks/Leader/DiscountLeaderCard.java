package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import java.util.ArrayList;

/**
 * This Class represents the leader card that give a discount
 */
public class DiscountLeaderCard extends LeaderCard {

    private final Resource discount;
    private final Type[] costOfLeaderCard;

    /**
     * Constructor method of this class
     */
    public DiscountLeaderCard(int victoryPoints, Resource discount, Type[] costOfLeaderCard){
        this.whatIAm = LeaderCardType.DISCOUNT;
        this.victoryPoints = victoryPoints;
        this.discount = discount;
        this.costOfLeaderCard = costOfLeaderCard;
    }

    /**
     * Getter of the parameter discount
     * @return the type of discount given by the card, of type Resource
     */
    public Resource getDiscount(){
        return discount;
    }

    /**
     * Getter of the parameter costOfLeaderCard
     * @return a copy of the array that represent the cost of the card, of type Type[]
     */
    public Type[] getCostOfLeaderCard(){
        return costOfLeaderCard.clone();
    }

    /**
     * This method exports the DiscountLeaderCard to a String
     * @return a string with all the card data, of type String
     */
    @Override
    public String export() {
        String result = "D";
        String fromVictoryPoints = String.valueOf(victoryPoints);
        String fromDiscount = "";
        switch (discount){
            case FAITH: fromDiscount = "0";
                break;
            case COIN: fromDiscount = "1";
                break;
            case STONE: fromDiscount = "2";
                break;
            case SERVANT: fromDiscount = "3";
                break;
            case SHIELD: fromDiscount = "4";
                break;
            case WHITE: fromDiscount = "5";
                break;
        }
        String fromCostOfLeaderCard = "";
        for(Type i : costOfLeaderCard){
            String toAdd = "";
            switch (i){
                case GREEN: toAdd = "g";
                    break;
                case BLUE: toAdd = "b";
                    break;
                case YELLOW: toAdd = "y";
                    break;
                case PURPLE: toAdd = "p";
                    break;
                default: break;
            }
            fromCostOfLeaderCard = new StringBuilder().append(fromCostOfLeaderCard).append(toAdd).toString();
        }
        result = new StringBuilder().append(result).append(fromVictoryPoints).append(fromDiscount).append(fromCostOfLeaderCard).toString();
        return result;
    }

    /**
     * Constructor method of this class from the exported string
     * @param importedString: the string to import
     */
    public DiscountLeaderCard(String importedString){
        this.whatIAm = LeaderCardType.DISCOUNT;
        victoryPoints = Integer.parseInt(importedString.substring(1, 2));
        String toAddDiscount = importedString.substring(2, 3);
        if(toAddDiscount.equals("0")){
            discount = Resource.FAITH;
        } else if(toAddDiscount.equals("1")){
            discount = Resource.COIN;
        } else if(toAddDiscount.equals("2")){
            discount = Resource.STONE;
        } else if(toAddDiscount.equals("3")){
            discount = Resource.SERVANT;
        } else if(toAddDiscount.equals("4")){
            discount = Resource.SHIELD;
        } else {
            discount = Resource.WHITE;
        }
        String fromCostOfLeaderCard = importedString.substring(3);
        ArrayList<Type> toCostOfLeaderCard = new ArrayList<>();
        for(int i = 0; i < fromCostOfLeaderCard.length(); i++){
            String toAdd = fromCostOfLeaderCard.substring(i, i+1);
            if(toAdd.equals("g")){
                toCostOfLeaderCard.add(Type.GREEN);
            } else if(toAdd.equals("b")){
                toCostOfLeaderCard.add(Type.BLUE);
            } else if(toAdd.equals("y")){
                toCostOfLeaderCard.add(Type.YELLOW);
            } else if(toAdd.equals("p")){
                toCostOfLeaderCard.add(Type.PURPLE);
            }
        }
        costOfLeaderCard = new Type[toCostOfLeaderCard.size()];
        toCostOfLeaderCard.toArray(costOfLeaderCard);
    }

}
