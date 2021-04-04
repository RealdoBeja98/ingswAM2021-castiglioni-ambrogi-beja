package it.polimi.ingsw.Table.Market;
import it.polimi.ingsw.PersonalBoard.Warehouse.DifferentResourceInThisShelfException;
import it.polimi.ingsw.PersonalBoard.Warehouse.PositionAlreadyOccupiedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.ResourceAlreadyPlacedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.Table.Deck.LeaderCard;
import it.polimi.ingsw.Table.Deck.OccupiedSlotExtraStorageLeaderCardException;

public abstract class Marble {

    public abstract void putResource(WarehouseDepots warehouseDepots, int pos) throws PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException;

    public abstract void putResource(LeaderCard leaderCard) throws DifferentStorageException, OccupiedSlotExtraStorageLeaderCardException;

    public abstract void putResource(FaithTrack faithTrack);

}
