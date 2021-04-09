package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Exceptions.OccupiedSlotExtraStorageLeaderCardException;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;


public class DiscountLeaderCard extends LeaderCard {

    private final Resource discount;
    private final Type[] costOfLeaderCard;

    public DiscountLeaderCard(int victoryPoints, Resource discount, Type[] costOfLeaderCard){
        this.whatIAm = LeaderCardType.DISCOUNT;
        this.victoryPoints = victoryPoints;
        this.discount = discount;
        this.costOfLeaderCard = costOfLeaderCard;
    }

    public Resource getDiscount(){
        return discount;
    }

    public Type[] getCostOfLeaderCard(){
        Type[] copy = costOfLeaderCard.clone();
        return copy;
    }

    @Override
    public Resource getStorageType() {
        throw new RuntimeException();
    }

    @Override
    public int occupiedResources() { throw new RuntimeException(); }

    @Override
    public void addResource() throws OccupiedSlotExtraStorageLeaderCardException { throw new RuntimeException(); }
}
