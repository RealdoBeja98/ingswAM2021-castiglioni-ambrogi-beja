package it.polimi.ingsw.Table.Market.Marbles;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.Table.Decks.Leader.ExtraStorageLeaderCard;
import it.polimi.ingsw.Table.Decks.Leader.LeaderCard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the class: Faith
 */
public class FaithMarbleTest {

    /**
     * This method tests the marbles behaviour when add a resource to a warehouse depot
     */
    @Test
    public void putResourceWarehouseTest() {
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        Faith faith = new Faith();
        assertThrows(RuntimeException.class, () ->{faith.putResource(warehouseDepots, 0);});
    }
    /**
     * This method tests the marbles behaviour when add a resource to a extra storage leader card
     */
    @Test
    public void putResourceLeaderTest() {
        LeaderCard extraStorage = new ExtraStorageLeaderCard(3, Resource.SHIELD, Resource.COIN);
        Faith faith = new Faith();
        assertThrows(RuntimeException.class, () ->{faith.putResource(extraStorage);});
    }

    /**
     * This method tests the marbles behaviour when add a resource to the faith track
     */
    @Test
    public void putResourceFaithTest() {
        FaithTrack faithTrack = new FaithTrack(Game.getInstance().getPlayers());
        Faith faith = new Faith();
        faith.putResource(faithTrack);
        assertSame(faithTrack.getFaithMarker(), 1);
    }
}