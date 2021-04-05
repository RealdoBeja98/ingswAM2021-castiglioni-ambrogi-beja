package it.polimi.ingsw.Table.Deck;

import it.polimi.ingsw.Resource;

public class DiscountLeaderCard extends LeaderCard{
    private Resource discount;
    private Type[] costOfLeaderCard = new Type[2];

    public DiscountLeaderCard(int victoryPoints, Resource discount, Type[] costOfLeaderCard){
        this.victoryPoints = victoryPoints;
        this.discount = discount;
        this.costOfLeaderCard = costOfLeaderCard;
    }

    @Override
    public Resource getStorageType() {
        throw new RuntimeException();
    }

    @Override
    public void addResource() throws OccupiedSlotExtraStorageLeaderCardException {
        throw new RuntimeException();
    }

    public Resource getDiscount(){
        return discount;
    }

    public Type[] getCostOfLeaderCard(){
        return costOfLeaderCard;
    }

}
