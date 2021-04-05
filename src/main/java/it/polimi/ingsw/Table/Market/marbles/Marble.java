package it.polimi.ingsw.Table.Market.marbles;
import it.polimi.ingsw.Exceptions.DifferentStorageException;
import it.polimi.ingsw.Exceptions.DifferentResourceInThisShelfException;
import it.polimi.ingsw.Exceptions.PositionAlreadyOccupiedException;
import it.polimi.ingsw.Exceptions.ResourceAlreadyPlacedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.Table.Deck.LeaderCard;
import it.polimi.ingsw.Exceptions.OccupiedSlotExtraStorageLeaderCardException;

/**
 * This Class is an abstraction of a marble
 */
public abstract class Marble {
    /**
     *abstract method: see implementation is son classes for the details
     */
    public abstract void putResource(WarehouseDepots warehouseDepots, int pos) throws PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException;

    /**
     *abstract method: see implementation is son classes for the details
     */
    public abstract void putResource(LeaderCard leaderCard) throws DifferentStorageException, OccupiedSlotExtraStorageLeaderCardException;

    /**
     *abstract method: see implementation is son classes for the details
     */
    public abstract void putResource(FaithTrack faithTrack);

}
