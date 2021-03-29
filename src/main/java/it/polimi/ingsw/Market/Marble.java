package it.polimi.ingsw.Market;
import it.polimi.ingsw.Deck.LeaderCard;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;

public abstract class Marble {

    public abstract void putResource(WarehouseDepots warehouseDepots, int pos);

    public abstract void putResource(LeaderCard leaderCard);

    public abstract void putResource(FaithTrack faithTrack);

}
