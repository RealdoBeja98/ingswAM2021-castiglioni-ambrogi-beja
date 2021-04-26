package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;

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

    @Override
    public String toString(){
        StringBuilder cost = new StringBuilder();
        for(Type i : costOfLeaderCard){
            cost.append(i.toString());
        }
        return "DiscountLeaderCard"+victoryPoints+discount+cost;
    }

}
