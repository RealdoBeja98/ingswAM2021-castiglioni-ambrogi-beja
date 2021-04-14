package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Exceptions.OccupiedSlotExtraStorageLeaderCardException;
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

    /**
     * This method only throws an exception when accessed by this card
     * @return the storage type of the card, of type Resource
     */
    @Override
    public Resource getStorageType() {
        throw new RuntimeException();
    }

    /**
     * This method only throws an exception when accessed by this card
     * @return the number of occupied slots, of type int
     */
    @Override
    public int occupiedResources() { throw new RuntimeException(); }

    /**
     * This method only throws an exception when accessed by this card
     */
    @Override
    public void addResource() throws OccupiedSlotExtraStorageLeaderCardException { throw new RuntimeException(); }
}
