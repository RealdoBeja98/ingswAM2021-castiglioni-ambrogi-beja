package it.polimi.ingsw.Table.Market.Marbles;
import it.polimi.ingsw.Table.Decks.Leader.ExtraStorageLeaderCard;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;

/**
 * This Class represents the marble of type faith
 */
public class Faith extends Marble {

    private final Resource whatIAm = Resource.FAITH;

    /**
     * This method only throws an exception when accessed by this marble
     * @param warehouseDepots: tells the method which player warehouse to use
     * @param pos: indicates one of the six position in the warehouse
     */
    @Override
    public void putResource(WarehouseDepots warehouseDepots, int pos) {
        throw new RuntimeException();
    }

    /**
     * This method only throws an exception when accessed by this marble
     * @param leaderCard: tells the method which card of the current player to use
     */
    @Override
    public void putResource(ExtraStorageLeaderCard leaderCard) {
        throw new RuntimeException();
    }

    /**
     * This method receives the current player faith track, then calls goOn on it to move the faith marker by one position
     * @param faithTrack: tells the method which faith track to use
     */
    @Override
    public void putResource(FaithTrack faithTrack) {
        faithTrack.goOn(1);
    }

    @Override
    public String toString(){
        return whatIAm.toString();
    }

}
