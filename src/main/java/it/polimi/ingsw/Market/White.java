package it.polimi.ingsw.Market;
import it.polimi.ingsw.PersonalBoard.FaithTrack;
import it.polimi.ingsw.Resource;
import it.polimi.ingsw.PersonalBoard.WarehouseDepots;

public class White extends Marble{
    private final Resource whatIAm = Resource.WHITE;

    @Override
    public void putResource(WarehouseDepots warehouseDepots, int pos) {

    }

    @Override
    public void putResource(LeaderCard leaderCard) {

    }

    @Override
    public void putResource(FaithTrack faithTrack) {
        throw new RuntimeException();
    }
}
