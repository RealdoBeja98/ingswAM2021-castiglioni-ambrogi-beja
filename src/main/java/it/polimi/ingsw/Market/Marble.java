package it.polimi.ingsw.Market;

import it.polimi.ingsw.Resource;

public abstract class Marble {
    public abstract void putResource(WarehouseDepots warehouseDepots, int pos);

    public abstract void putResource(LeaderCard leaderCard);

    public abstract void putResource(FaithTrack faithTrack);

    public abstract Resource whatIAm();


}
