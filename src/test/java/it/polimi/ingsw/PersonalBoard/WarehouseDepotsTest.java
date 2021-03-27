package it.polimi.ingsw.PersonalBoard;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.PersonalBoard.Warehouse.*;
import it.polimi.ingsw.Resource;
import org.junit.jupiter.api.Test;

public class WarehouseDepotsTest {

    /**
     * We are testing adding a resource
     */
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

    /**
     * We are testing adding multiple resources
     */
    @Test
    public void testAddResources(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,0);
            warehouseDepots.addResource(Resource.SERVANT,1);
            warehouseDepots.addResource(Resource.SERVANT,2);
            warehouseDepots.addResource(Resource.SHIELD,3);
            warehouseDepots.addResource(Resource.SHIELD,4);
            warehouseDepots.addResource(Resource.SHIELD,5);
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        } catch (ResourceAlreadyPlacedException e) {
            e.printStackTrace();
        } catch (DifferentResourceInThisShelfException e) {
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
     * We are testing putting two resources on the same position
     */
    @Test
    public void testAddException1(){
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
        assertThrows(PositionAlreadyOccupiedException.class, () ->{warehouseDepots.addResource(Resource.COIN,2);});
    }

    /**
     * We are testing adding the same resource on a different shelf
     */
    @Test
    public void testAddException2(){
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
        assertThrows(ResourceAlreadyPlacedException.class, () ->{warehouseDepots.addResource(Resource.COIN,5);});
    }

    /**
     * We are testing adding different resource on the same shelf
     */
    @Test
    public void testAddException3(){
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
        assertThrows(DifferentResourceInThisShelfException.class, () ->{warehouseDepots.addResource(Resource.SERVANT,1);});
    }

    /**
     * We are testing removing a resource
     */
    @Test
    public void removeTest(){
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
        warehouseDepots.removeResource(2);
        assertNull(warehouseDepots.getResource()[2]);
    }

    /**
     * We are testing exchanging a resource
     */
    @Test
    public void exchangingTest(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,1);
            warehouseDepots.addResource(Resource.SERVANT,4);
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        } catch (ResourceAlreadyPlacedException e) {
            e.printStackTrace();
        } catch (DifferentResourceInThisShelfException e) {
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
     * We are testing moving a resource in an empty slot
     */
    @Test
    public void moveTest(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,1);
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        } catch (ResourceAlreadyPlacedException e) {
            e.printStackTrace();
        } catch (DifferentResourceInThisShelfException e) {
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
     * We are testing moving a resource out of bound
     */
    @Test
    public void testMoveException1(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,1);
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        } catch (ResourceAlreadyPlacedException e) {
            e.printStackTrace();
        } catch (DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        assertThrows(IndexOutOfBoundsException.class, () ->{warehouseDepots.moveResource(1,6);});
    }

    /**
     * We are testing moving a resource to a shelf whit other resources type
     */
    @Test
    public void testMoveException2(){
        WarehouseDepots warehouseDepots = new WarehouseDepots();
        try {
            warehouseDepots.addResource(Resource.COIN,0);
            warehouseDepots.addResource(Resource.SERVANT,3);
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        } catch (ResourceAlreadyPlacedException e) {
            e.printStackTrace();
        } catch (DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        assertThrows(NotAdmittedMovementException.class, () ->{warehouseDepots.moveResource(0,4);});
    }

}
