package it.polimi.ingsw.Table.Deck;

import it.polimi.ingsw.Resource;
import it.polimi.ingsw.Table.Market.Marble;

public class WhiteMarbleLeaderCard extends LeaderCard {
    private Type[] costOfLeaderCard = new Type[3];
    private Marble whiteMarble;

    public WhiteMarbleLeaderCard(int victoryPoints, Type[] costOfLeaderCard, Marble whiteMarble){
        this.victoryPoints = victoryPoints;
        this.costOfLeaderCard = costOfLeaderCard;
        this.whiteMarble = whiteMarble;
    }

    @Override
    public Resource getStorageType() {
        return null;
    }

    @Override
    public void addResource() throws OccupiedSlotExtraStorageLeaderCardException {
    }

    public Type[] getCostOfLeaderCard(){
        return costOfLeaderCard;
    }

    public Marble getWhiteMarble(){
        return whiteMarble;
    }
}
