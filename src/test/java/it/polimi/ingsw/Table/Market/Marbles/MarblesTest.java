package it.polimi.ingsw.Table.Market.Marbles;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.Table.Decks.Leader.ExtraStorageLeaderCard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the class: Coin, Servant, Stone and Shield
 */
public class MarblesTest {

    /**
     * This method tests the marbles behaviour when add a resource to a warehouse depot
     */
    @Test
    void putResourceWarehouseTest(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        Coin coin = new Coin();
        try {
            coin.putResource(warehouseDepots, 0);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException | IndexOutOfWarehouseDepotsException e) {
            e.printStackTrace();
        }
        assertSame(warehouseDepots.getResource()[0], Resource.COIN);
        assertThrows(PositionAlreadyOccupiedException.class, () -> coin.putResource(warehouseDepots,0));
        assertThrows(IndexOutOfWarehouseDepotsException.class, () -> coin.putResource(warehouseDepots,10));
        Servant servant = new Servant();
        Shield shield = new Shield();
        try {
            servant.putResource(warehouseDepots, 1);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException | IndexOutOfWarehouseDepotsException e) {
            e.printStackTrace();
        }
        assertThrows(DifferentResourceInThisShelfException.class, () -> shield.putResource(warehouseDepots,2));
        assertThrows(ResourceAlreadyPlacedException.class, () -> coin.putResource(warehouseDepots,2));
    }

    /**
     * This method tests the marbles behaviour when add a resource to a extra storage leader card
     */
    @Test
    void putResourceLeaderTest(){
        ExtraStorageLeaderCard extraStorage = new ExtraStorageLeaderCard(3, Resource.SHIELD, Resource.COIN);
        Coin coin = new Coin();
        try {
            coin.putResource(extraStorage);
        } catch (DifferentStorageException | OccupiedSlotExtraStorageLeaderCardException e) {
            e.printStackTrace();
        }
        assertSame(extraStorage.occupiedResources(), 1);
        Shield shield = new Shield();
        assertThrows(DifferentStorageException.class, () -> shield.putResource(extraStorage));
        try {
            coin.putResource(extraStorage);
        } catch (DifferentStorageException | OccupiedSlotExtraStorageLeaderCardException e) {
            e.printStackTrace();
        }
        assertSame(extraStorage.occupiedResources(), 2);
        assertThrows(OccupiedSlotExtraStorageLeaderCardException.class, () -> coin.putResource(extraStorage));
    }

    /**
     * This method tests the marbles behaviour when add a resource to the faith track
     */
    @Test
    void putResourceFaithTest(){
        FaithTrack faithTrack = new FaithTrack(200);
        Coin coin = new Coin();
        assertThrows(RuntimeException.class, () -> coin.putResource(faithTrack));
    }

}
