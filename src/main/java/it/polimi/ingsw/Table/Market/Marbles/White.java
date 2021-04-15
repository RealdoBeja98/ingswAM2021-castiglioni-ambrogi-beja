package it.polimi.ingsw.Table.Market.Marbles;
import it.polimi.ingsw.Table.Decks.Leader.ExtraStorageLeaderCard;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;

/**
 * This Class represents the marble of type white
 */
public class White extends Marble {

    private final Resource whatIAm = Resource.WHITE;

    /**
     * this method does nothing
     * @param warehouseDepots: tells the method which player warehouse to use
     * @param pos: indicates one of the six position in the warehouse
     */
    @Override
    public void putResource(WarehouseDepots warehouseDepots, int pos) { }

    /**
     * this method does nothing
     * @param leaderCard: tells the method which card of the current player to use
     */
    @Override
    public void putResource(ExtraStorageLeaderCard leaderCard) { }

    /**
     * This method only throws an exception when accessed by this marble
     * @param faithTrack: tells the method which faith track to use
     */
    @Override
    public void putResource(FaithTrack faithTrack) {
        throw new RuntimeException();
    }

}
