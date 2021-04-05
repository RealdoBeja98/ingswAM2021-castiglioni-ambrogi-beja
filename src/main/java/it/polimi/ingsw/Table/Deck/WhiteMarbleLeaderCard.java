package it.polimi.ingsw.Table.Deck;

import it.polimi.ingsw.Exceptions.OccupiedSlotExtraStorageLeaderCardException;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Table.Market.marbles.Marble;

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
        throw new RuntimeException();
    }

    @Override
    public void addResource() throws OccupiedSlotExtraStorageLeaderCardException {
        throw new RuntimeException();
    }

    public Type[] getCostOfLeaderCard(){
        return costOfLeaderCard;
    }

    public Marble getWhiteMarble(){
        return whiteMarble;
    }
}