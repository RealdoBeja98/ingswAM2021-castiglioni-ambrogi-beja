package it.polimi.ingsw.Market;

import it.polimi.ingsw.Resource;

public class Faith extends Marble{

    @Override
    public void putResource(WarehouseDepots warehouseDepots, int pos) {
        throw new RuntimeException();
    }

    @Override
    public void putResource(LeaderCard leaderCard) {
        throw new RuntimeException();
    }

    @Override
    public void putResource(FaithTrack faithTrack) {

    }

    @Override
    public Resource whatIAm() {
        return null;
    }

}
