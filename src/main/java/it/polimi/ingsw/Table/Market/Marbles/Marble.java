package it.polimi.ingsw.Table.Market.Marbles;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.Table.Decks.Leader.ExtraStorageLeaderCard;

/**
 * This Class is an abstraction of a marble
 */
public abstract class Marble {
    /**
     * protected variable wht i am of type resource
     */
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
     * @param pos integer
     * @param warehouseDepots warehouse depots
     * @throws PositionAlreadyOccupiedException  position already occupied
     * @throws ResourceAlreadyPlacedException  resource already placed
     * @throws DifferentResourceInThisShelfException different resource in this shelf message
     * @throws IndexOutOfWarehouseDepotsException index out of bounds
     */
    public abstract void putResource(WarehouseDepots warehouseDepots, int pos) throws PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException, IndexOutOfWarehouseDepotsException;

    /**
     *abstract method: see implementation in son classes for the details
     * @param leaderCard extra storage leader card
     * @throws DifferentStorageException different storage exception
     *@throws OccupiedSlotExtraStorageLeaderCardException occupied slot extra storage leader card exception
     */
    public abstract void putResource(ExtraStorageLeaderCard leaderCard) throws DifferentStorageException, OccupiedSlotExtraStorageLeaderCardException;

    /**
     *abstract method: see implementation in son classes for the details
     * @param faithTrack faith track
     */
    public abstract void putResource(FaithTrack faithTrack);

}
