package it.polimi.ingsw.PersonalBoard;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.PersonalBoard.Warehouse.DifferentResourceInThisShelfException;
import it.polimi.ingsw.PersonalBoard.Warehouse.PositionAlreadyOccupiedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.ResourceAlreadyPlacedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.Resource;
import org.junit.jupiter.api.Test;

public class WarehouseDepotsTest {

    @Test
    public void testAddResource(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,2);
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        } catch (ResourceAlreadyPlacedException e) {
            e.printStackTrace();
        } catch (DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        assertSame(warehouseDepots.getResource()[2], Resource.COIN);

    }



}
