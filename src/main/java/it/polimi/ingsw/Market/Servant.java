package it.polimi.ingsw.Market;
import it.polimi.ingsw.Deck.LeaderCard;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.PersonalBoard.Warehouse.DifferentResourceInThisShelfException;
import it.polimi.ingsw.PersonalBoard.Warehouse.PositionAlreadyOccupiedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.ResourceAlreadyPlacedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.Resource;

public class Servant extends Marble{

    private final Resource whatIAm = Resource.SERVANT;

    @Override
    public void putResource(WarehouseDepots warehouseDepots, int pos) {
        try {
            warehouseDepots.addResource(whatIAm, pos);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException e) {
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
