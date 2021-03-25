package it.polimi.ingsw.Market;
import it.polimi.ingsw.PersonalBoard.FaithTrack;
import it.polimi.ingsw.PersonalBoard.PositionAlreadyOccupiedException;
import it.polimi.ingsw.PersonalBoard.ResourceAlreadyPlacedException;
import it.polimi.ingsw.Resource;
import it.polimi.ingsw.PersonalBoard.WarehouseDepots;

public class Stone extends Marble{
    private final Resource whatIAm = Resource.STONE;
    @Override
    public void putResource(WarehouseDepots warehouseDepots, int pos) {
        try {
            warehouseDepots.addResource(whatIAm, pos);
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        } catch (ResourceAlreadyPlacedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putResource(LeaderCard leaderCard) {
        leaderCard.addResource(whatIAm);
    }

    @Override
    public void putResource(FaithTrack faithTrack) {
        throw new RuntimeException();
    }


}
