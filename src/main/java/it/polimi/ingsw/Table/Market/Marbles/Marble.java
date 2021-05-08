package it.polimi.ingsw.Table.Market.Marbles;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.DifferentStorageException;
import it.polimi.ingsw.Exceptions.DifferentResourceInThisShelfException;
import it.polimi.ingsw.Exceptions.PositionAlreadyOccupiedException;
import it.polimi.ingsw.Exceptions.ResourceAlreadyPlacedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.Table.Decks.Leader.ExtraStorageLeaderCard;
import it.polimi.ingsw.Exceptions.OccupiedSlotExtraStorageLeaderCardException;

/**
 * This Class is an abstraction of a marble
 */
public abstract class Marble {

    protected Resource whatIAm;

    /**
     * Getter of the parameter whatIAm
     * @return the type of the marble, of type Resource
     */
    public Resource getWhatIAm(){
        return whatIAm;
    }

    /**
     *abstract method: see implementation in son classes for the details
     */
    public abstract void putResource(WarehouseDepots warehouseDepots, int pos) throws PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException;

    /**
     *abstract method: see implementation in son classes for the details
     */
    public abstract void putResource(ExtraStorageLeaderCard leaderCard) throws DifferentStorageException, OccupiedSlotExtraStorageLeaderCardException;

    /**
     *abstract method: see implementation in son classes for the details
     */
    public abstract void putResource(FaithTrack faithTrack);

}
