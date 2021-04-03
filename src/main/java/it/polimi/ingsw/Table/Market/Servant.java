package it.polimi.ingsw.Table.Market;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.PersonalBoard.Warehouse.DifferentResourceInThisShelfException;
import it.polimi.ingsw.PersonalBoard.Warehouse.PositionAlreadyOccupiedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.ResourceAlreadyPlacedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.Resource;
import it.polimi.ingsw.Table.Deck.LeaderCard;
import it.polimi.ingsw.Table.Deck.OccupiedSlotExtraStorageLeaderCardException;

public class Servant extends Marble{

    private final Resource whatIAm = Resource.SERVANT;

    @Override
    public void putResource(WarehouseDepots warehouseDepots, int pos) throws PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException {
        warehouseDepots.addResource(whatIAm, pos);
    }

    @Override
    public void putResource(LeaderCard leaderCard) throws DifferentStorageException, OccupiedSlotExtraStorageLeaderCardException {
        if(leaderCard.getStorageType() == whatIAm){
            leaderCard.addResource();
        }
        else{
            throw new DifferentStorageException();
        }
    }

    @Override
    public void putResource(FaithTrack faithTrack) {
        throw new RuntimeException();
    }

}
