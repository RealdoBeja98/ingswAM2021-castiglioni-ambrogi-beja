package it.polimi.ingsw.PersonalBoard.Warehouse;
import static org.junit.jupiter.api.Assertions.*;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Enums.Resource;
import org.junit.jupiter.api.Test;

/**
 * Test class for the class: WarehouseDepots
 */
public class WarehouseDepotsTest {

    /**
     * This method tests the addition of a resource in the warehouse
     */
    @Test
    void testAddResource(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,2);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        assertSame(warehouseDepots.getResource()[2], Resource.COIN);
    }

    /**
     * This method tests the addition of resources in the warehouse
     */
    @Test
    void testAddResources(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,0);
            warehouseDepots.addResource(Resource.SERVANT,1);
            warehouseDepots.addResource(Resource.SERVANT,2);
            warehouseDepots.addResource(Resource.SHIELD,3);
            warehouseDepots.addResource(Resource.SHIELD,4);
            warehouseDepots.addResource(Resource.SHIELD,5);
        } catch (PositionAlreadyOccupiedException | DifferentResourceInThisShelfException | ResourceAlreadyPlacedException e) {
            e.printStackTrace();
        }
        assertSame(warehouseDepots.getResource()[0], Resource.COIN);
        assertSame(warehouseDepots.getResource()[1], Resource.SERVANT);
        assertSame(warehouseDepots.getResource()[2], Resource.SERVANT);
        assertSame(warehouseDepots.getResource()[3], Resource.SHIELD);
        assertSame(warehouseDepots.getResource()[4], Resource.SHIELD);
        assertSame(warehouseDepots.getResource()[5], Resource.SHIELD);
    }

    /**
     * This method tests the exception during the addition of resources in the same place in the warehouse
     */
    @Test
    void testAddException1(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,2);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        assertThrows(PositionAlreadyOccupiedException.class, () -> warehouseDepots.addResource(Resource.COIN,2));
    }

    /**
     * This method tests the exception during the addition of the same resource in different shelves in the warehouse
     */
    @Test
    void testAddException2(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,2);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        assertThrows(ResourceAlreadyPlacedException.class, () -> warehouseDepots.addResource(Resource.COIN,5));
    }

    /**
     * This method tests the exception during the addition of the same resource in the same shelves in the warehouse
     */
    @Test
    void testAddException3(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,2);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        assertThrows(DifferentResourceInThisShelfException.class, () -> warehouseDepots.addResource(Resource.SERVANT,1));
    }

    /**
     * This method tests the subtraction of resources from the warehouse
     */
    @Test
    void removeTest(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,2);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        try {
            warehouseDepots.removeResource(2);
        } catch (YetEmptySlotException e) {
            e.printStackTrace();
        }
        assertNull(warehouseDepots.getResource()[2]);
    }

    /**
     * This method tests the movement between two resources
     */
    @Test
    void exchangingTest(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,1);
            warehouseDepots.addResource(Resource.SERVANT,4);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        try {
            warehouseDepots.moveResource(1,4);
        } catch (NotAdmittedMovementException e) {
            e.printStackTrace();
        }
        assertSame(warehouseDepots.getResource()[1], Resource.SERVANT);
        assertSame(warehouseDepots.getResource()[4], Resource.COIN);
    }

    /**
     * This method tests the movement of a resource into an empty slot
     */
    @Test
    void moveTest(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,1);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        try {
            warehouseDepots.moveResource(1,2);
        } catch (NotAdmittedMovementException e) {
            e.printStackTrace();
        }
        assertNull(warehouseDepots.getResource()[1]);
        assertSame(warehouseDepots.getResource()[2], Resource.COIN);
    }

    /**
     * This method tests the movement of a resource out of bound
     */
    @Test
    void testMoveException1(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,1);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        assertThrows(IndexOutOfBoundsException.class, () -> warehouseDepots.moveResource(1,6));
    }

    /**
     * This method tests the movement of a resource to a shelf with other resource type
     */
    @Test
    void testMoveException2(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,0);
            warehouseDepots.addResource(Resource.SERVANT,3);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        assertThrows(NotAdmittedMovementException.class, () -> warehouseDepots.moveResource(0,4));
    }

    /**
     * This method tests the getter of the class and their return
     */
    @Test
    void integrityTest(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,0);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        Resource[] test = warehouseDepots.getResource();
        assertSame(warehouseDepots.getResource()[0], Resource.COIN);
        test[0] = Resource.SERVANT;
        assertSame(warehouseDepots.getResource()[0], Resource.COIN);
        assertSame(test[0], Resource.SERVANT);
        try {
            warehouseDepots.moveResource(0,1);
        } catch (NotAdmittedMovementException e) {
            e.printStackTrace();
        }
        assertSame(warehouseDepots.getResource()[1], Resource.COIN);
        test = warehouseDepots.getResource();
        assertNull(test[0]);
        assertSame(test[1], Resource.COIN);

    }
}
