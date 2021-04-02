package it.polimi.ingsw.Table.Market;
import it.polimi.ingsw.Table.Deck.LeaderCard;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;

public abstract class Marble {

    public abstract void putResource(WarehouseDepots warehouseDepots, int pos);

    public abstract void putResource(LeaderCard leaderCard);

    public abstract void putResource(FaithTrack faithTrack);

}
