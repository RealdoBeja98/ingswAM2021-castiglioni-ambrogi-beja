package it.polimi.ingsw.Table.Market;
import it.polimi.ingsw.Table.Deck.LeaderCard;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.Resource;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;

public class White extends Marble{

    private final Resource whatIAm = Resource.WHITE;

    @Override
    public void putResource(WarehouseDepots warehouseDepots, int pos) { }

    @Override
    public void putResource(LeaderCard leaderCard) { }

    @Override
    public void putResource(FaithTrack faithTrack) {
        throw new RuntimeException();
    }

}
