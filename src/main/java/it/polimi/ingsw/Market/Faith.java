package it.polimi.ingsw.Market;
import it.polimi.ingsw.PersonalBoard.FaithTrack;
import it.polimi.ingsw.Resource;

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
