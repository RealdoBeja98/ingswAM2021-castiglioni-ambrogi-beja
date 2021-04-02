package it.polimi.ingsw.Table.Market;
import it.polimi.ingsw.Table.Deck.LeaderCard;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.Resource;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;

public class Faith extends Marble{

    private final Resource whatIAm = Resource.FAITH;

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
        faithTrack.goOn(1);
    }

}
