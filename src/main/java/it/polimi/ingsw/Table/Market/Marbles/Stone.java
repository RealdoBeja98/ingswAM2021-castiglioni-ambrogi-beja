package it.polimi.ingsw.Table.Market.Marbles;
import it.polimi.ingsw.Exceptions.DifferentStorageException;
import it.polimi.ingsw.Table.Decks.Leader.ExtraStorageLeaderCard;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.Exceptions.DifferentResourceInThisShelfException;
import it.polimi.ingsw.Exceptions.PositionAlreadyOccupiedException;
import it.polimi.ingsw.Exceptions.ResourceAlreadyPlacedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.OccupiedSlotExtraStorageLeaderCardException;

/**
 * This Class represents the marble of type stone
 */
public class Stone extends Marble {

    /**
     * Constructor method of this class
     */
    public Stone(){
        whatIAm = Resource.STONE;
    }

    /**
     * This method receives the current player warehouse depots and a position in it, then calls
     * addResource on it, passing what type is this marble
     * @param warehouseDepots: tells the method which player warehouse to use
     * @param pos: indicates one of the six position in the warehouse
     * @throws PositionAlreadyOccupiedException: propagates this exception, generated if the position chosen is already occupied
     * @throws ResourceAlreadyPlacedException: propagates this exception, generated if the resource passed is present on a different shelf
     * @throws DifferentResourceInThisShelfException: propagates this exception, generated if there are different resources types already
     *                                                placed in the chosen shelf
     */
    @Override
    public void putResource(WarehouseDepots warehouseDepots, int pos) throws PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException {
        warehouseDepots.addResource(whatIAm, pos);
    }

    /**
     * This method receives an already placed leader card of the current player then calls addResource on it,
     * passing what type is this marble
     * @param leaderCard: tells the method which card of the current player to use
     * @throws DifferentStorageException : if the storage passed has a different type of this marble
     * @throws OccupiedSlotExtraStorageLeaderCardException: propagates this exception, generated if the leader card slots are full
     */
    @Override
    public void putResource(ExtraStorageLeaderCard leaderCard) throws DifferentStorageException, OccupiedSlotExtraStorageLeaderCardException {
        if(leaderCard.getStorageType() == whatIAm){
            leaderCard.addResource();
        }
        else{
            throw new DifferentStorageException();
        }
    }

    /**
     * This method only throws an exception when accessed by this marble
     * @param faithTrack: tells the method which faith track to use
     */
    @Override
    public void putResource(FaithTrack faithTrack) {
        throw new RuntimeException();
    }

    /**
     * This method overrides the classic toString
     * @return the type of the marble in a string, of type String
     */
    @Override
    public String toString(){
        return whatIAm.toString();
    }

}
