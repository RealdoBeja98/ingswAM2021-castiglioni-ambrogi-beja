package it.polimi.ingsw.Table.Market;
import it.polimi.ingsw.Table.Deck.LeaderCard;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.PersonalBoard.Warehouse.DifferentResourceInThisShelfException;
import it.polimi.ingsw.PersonalBoard.Warehouse.PositionAlreadyOccupiedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.ResourceAlreadyPlacedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.Resource;

public class Stone extends Marble{

    private final Resource whatIAm = Resource.STONE;

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
