package it.polimi.ingsw.Market;
import it.polimi.ingsw.PersonalBoard.FaithTrack;
import it.polimi.ingsw.Resource;
import it.polimi.ingsw.PersonalBoard.WarehouseDepots;

public class Coin extends Marble {
    private final Resource whatIAm = Resource.COIN;

    @Override
    public void putResource(WarehouseDepots warehouseDepots, int pos) {
        WarehouseDepots.addResource(whatIAm, pos);
    }

    @Override
    public void putResource(LeaderCard leaderCard) {
        LeaderCard.addResource(whatIAm);
    }

    @Override
    public void putResource(FaithTrack faithTrack) {
        throw new RuntimeException();
    }


}
