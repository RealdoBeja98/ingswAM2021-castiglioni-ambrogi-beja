package it.polimi.ingsw.Market;

import it.polimi.ingsw.Resource;

public class Stone extends Marble{
    @Override
    public void putResource(WarehouseDepots warehouseDepots, int pos) {
        WarehouseDepots.addResource(whatIAm(), pos);
    }

    @Override
    public void putResource(LeaderCard leaderCard) {
        LeaderCard.addResource(whatIAm());
    }

    @Override
    public void putResource(FaithTrack faithTrack) {

    }

    @Override
    public Resource whatIAm() {
        return Resource.STONE;
    }
}
