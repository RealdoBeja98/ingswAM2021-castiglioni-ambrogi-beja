package it.polimi.ingsw;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Player;
import org.junit.jupiter.api.Test;

/**
 * Test class for the class: Player
 */
public class PlayerTest { //<--FIXME to many instances of player-->
    /**
     * This method tests discarding a leader card
     */
    @Test
    void testDiscardLeaderCard() {
        Player player = new Player("Carlo");
        try {
            player.discardLeaderCard(2);
        } catch (YetDiscardedThisLeaderCardException e) {
            e.printStackTrace();
        }
        assertSame(1, player.getPersonalBoard().getFaithTrack().getFaithMarker());
        assertNull(player.getCardsInHand()[1]);
    }

    /**
     * This method tests taking resources from the market
     */
   /* @Test
    void testTakeResourcesFromTheMarket() {
        Player player = new Player("Andrea");
        assertFalse(player.resourceToAdd());
        player.takeResourcesFromTheMarket(RowColumn.ROW, 2);
        assertTrue(player.resourceToAdd());
    }
*/
    /**
     * This method tests moving resources in ware hose depots
     */
  /*  @Test
    void testMoveResourcesInWarehouseDepots() {
        Player player = new Player("Aldo");
        try {
            player.getPersonalBoard().getWarehouseDepots().addResource(Resource.COIN, 0);
            player.getPersonalBoard().getWarehouseDepots().addResource(Resource.SERVANT, 1);
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        } catch (ResourceAlreadyPlacedException e) {
            e.printStackTrace();
        } catch (DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        try {
            player.selectAWarehouseDepotsSlot(0);
        } catch (PositionInvalidException e) {
            e.printStackTrace();
        }
        try {
            player.moveResourcesInWarehouseDepots(1);
        } catch (NotAdmittedMovementException e) {
            e.printStackTrace();
        }
        assertSame(player.getPersonalBoard().getWarehouseDepots().getResource()[0], Resource.SERVANT);
        assertSame(player.getPersonalBoard().getWarehouseDepots().getResource()[1], Resource.COIN);
        try {
            player.moveResourcesInWarehouseDepots(5);
        } catch (NotAdmittedMovementException e) {
            e.printStackTrace();
        }
        assertSame(player.getPersonalBoard().getWarehouseDepots().getResource()[5], Resource.SERVANT);
        assertNull(player.getPersonalBoard().getWarehouseDepots().getResource()[0]);
    }
*/
    /**
     * This method tests the possibility of activating a power production
     */
    @Test
    void testCanYouActivateDefaultPowerProduction() {
        Player player = new Player("Carlo");
        assertFalse(player.canYouActivateAPowerProduction());
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.COIN, 2);
        } catch (NotAResourceForStrongBoxException e) {
            e.printStackTrace();
        }
        assertTrue(player.canYouActivateAPowerProduction());
    }

    /**
     * This method tests an example of production
     */
    /*@Test
    void testExamplePowerProduction() {
        Player player = new Player("Aldo");
        try {
            player.getPersonalBoard().getWarehouseDepots().addResource(Resource.COIN, 0);
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        } catch (ResourceAlreadyPlacedException e) {
            e.printStackTrace();
        } catch (DifferentResourceInThisShelfException e) {
            e.printStackTrace();
        }
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.SHIELD, 1);
        } catch (NotAResourceForStrongBoxException e) {
            e.printStackTrace();
        }
        player.selectDefaultProductionPower();
        try {
            player.startPayment();
        } catch (NotEnoughResourcesException e) {
            e.printStackTrace();
        } catch (YouHaveNotSelectedAnyProductionException e) {
            e.printStackTrace();
        }
        try {
            player.payWithWarehouseDepots(0);
        } catch (WrongPaymentException e) {
            e.printStackTrace();
        } catch (YetEmptySlotException e) {
            e.printStackTrace();
        } catch (NoResourceToPayException e) {
            e.printStackTrace();
        }
        try {
            player.payWithStrongBox(Resource.SHIELD);
        } catch (WrongPaymentException e) {
            e.printStackTrace();
        } catch (NegativeResourceException e) {
            e.printStackTrace();
        } catch (NotAResourceForStrongBoxException e) {
            e.printStackTrace();
        } catch (NoResourceToPayException e) {
            e.printStackTrace();
        }
        try {
            player.obtainGenericResource(Resource.SERVANT);
        } catch (NoGenericResourceToObtainException e) {
            e.printStackTrace();
        } catch (NotAResourceForStrongBoxException e) {
            e.printStackTrace();
        }
        assertNull(player.getPersonalBoard().getWarehouseDepots().getResource()[0]);
        assertSame(player.getPersonalBoard().getStrongBox().getServant(), 1);
        assertSame(player.getPersonalBoard().getStrongBox().getShield(), 0);
    }
    */
}
