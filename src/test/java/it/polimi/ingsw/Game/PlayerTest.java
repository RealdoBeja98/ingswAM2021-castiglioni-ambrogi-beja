package it.polimi.ingsw.Game;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the class: Player
 */
class PlayerTest {

    @Test
    void setInkwell() {
    }

    @Test
    void getPersonalBoard() {
    }

    @Test
    void getNickname() {
    }

    @Test
    void getCardsInHand() {
    }

    @Test
    void getCardsOnTable() {
    }

    @Test
    void getActiveDiscount() {
    }

    @Test
    void isInkwell() {
    }

    /**
     * This method tests discarding a leader card
     */
    @Test
    void testDiscardLeaderCard() {
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException e) {
            e.printStackTrace();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.discardLeaderCard(2);
        } catch (YetDiscardedThisLeaderCardException e) {
            e.printStackTrace();
        }
        assertSame(1, player.getPersonalBoard().getFaithTrack().getFaithMarker());
        assertNull(player.getCardsInHand()[1]);
    }

    @Test
    void canYouPlayAtLeastALeaderCard() {
    }

    @Test
    void playLeaderCard() {
    }

    /**
     * This method tests taking resources from the market
     */
    @Test
    void testTakeResourcesFromTheMarket() {
        Game game = new Game(1);
        try {
            game.addPlayer("Andrea");
        } catch (NameAlreadyRegisteredException e) {
            e.printStackTrace();
        }
        Player player = game.getPlayers().get(0);
        assertFalse(player.resourceToAdd());
        player.takeResourcesFromTheMarket(RowColumn.ROW, 2);
        assertTrue(player.resourceToAdd());
    }

    /**
     * This method tests moving resources in ware hose depots
     */
    @Test
    void testMoveResourcesInWarehouseDepots() {
        Game game = new Game(1);
        try {
            game.addPlayer("Aldo");
        } catch (NameAlreadyRegisteredException e) {
            e.printStackTrace();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.getPersonalBoard().getWarehouseDepots().addResource(Resource.COIN, 0);
            player.getPersonalBoard().getWarehouseDepots().addResource(Resource.SERVANT, 1);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException e) {
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

    @Test
    void moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard() {
    }

    @Test
    void moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard() {
    }

    @Test
    void resourceToAdd() {
    }

    @Test
    void whichResourceToAdd() {
    }

    @Test
    void addResource() {
    }

    @Test
    void changeWhiteMarbleWith() {
    }

    /**
     * This method tests the possibility of activating a power production
     */
    @Test
    void testCanYouActivateDefaultPowerProduction() {
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException e) {
            e.printStackTrace();
        }
        Player player = game.getPlayers().get(0);
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
    @Test
    void testExamplePowerProduction() {
        Game game = new Game(1);
        try {
            game.addPlayer("Aldo");
        } catch (NameAlreadyRegisteredException e) {
            e.printStackTrace();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.getPersonalBoard().getWarehouseDepots().addResource(Resource.COIN, 0);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException e) {
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
        } catch (NotEnoughResourcesException | YouHaveNotSelectedAnyProductionException e) {
            e.printStackTrace();
        }
        try {
            player.payWithWarehouseDepots(0);
        } catch (WrongPaymentException | YetEmptySlotException | NoResourceToPayException e) {
            e.printStackTrace();
        }
        try {
            player.payWithStrongBox(Resource.SHIELD);
        } catch (WrongPaymentException | NotAResourceForStrongBoxException | NegativeResourceException | NoResourceToPayException e) {
            e.printStackTrace();
        }
        try {
            player.obtainGenericResource(Resource.SERVANT);
        } catch (NoGenericResourceToObtainException | NotAResourceForStrongBoxException e) {
            e.printStackTrace();
        }
        assertNull(player.getPersonalBoard().getWarehouseDepots().getResource()[0]);
        assertSame(player.getPersonalBoard().getStrongBox().getServant(), 1);
        assertSame(player.getPersonalBoard().getStrongBox().getShield(), 0);
    }

    @Test
    void selectProductionDevelopmentCard() {
    }

    @Test
    void selectProductionPowerLeaderCard() {
    }

    @Test
    void somethingToPay() {
    }

    @Test
    void payWithExtraStorageLeaderCard() {
    }

    @Test
    void genericResourcesToObtain() {
    }

    @Test
    void obtainGenericResource() {
    }

    @Test
    void canYouBuyADevelopmentCard() {
    }

    @Test
    void buyADevelopmentCard() {
    }

    @Test
    void developmentCardToObtain() {
    }

    @Test
    void obtainDevelopmentCard() {
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException e) {
            e.printStackTrace();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.COIN,100);
            player.getPersonalBoard().getStrongBox().add(Resource.SERVANT,100);
            player.getPersonalBoard().getStrongBox().add(Resource.SHIELD,100);
            player.getPersonalBoard().getStrongBox().add(Resource.STONE,100);
        } catch (NotAResourceForStrongBoxException e) {
            e.printStackTrace();
        }
        DevelopmentCard developmentCard = game.getTable().getDevelopmentDeck().visualize()[2][0];
        try {
            player.buyADevelopmentCard(3,1);
        } catch (PositionInvalidException | NoDevelopmentCardInThisPositionException | NotAbleToBuyThisDevelopmentCardException | NotAbleToPlaceThisDevelopmentCardException | DrawnFromEmptyDeckException e) {
            e.printStackTrace();
        }
        while(player.somethingToPay()){
            try {
                player.payWithStrongBox(player.nextToPay());
            } catch (WrongPaymentException | NegativeResourceException | NotAResourceForStrongBoxException | NoResourceToPayException e) {
                e.printStackTrace();
            }
        }
        try {
            player.obtainDevelopmentCard(1);
        } catch (NoDevelopmentCardToObtainException | PositionInvalidException e) {
            e.printStackTrace();
        }
        assertSame(developmentCard, player.getPersonalBoard().getSlotsDevelopmentCards().getActiveCards()[0]);
    }

    @Test
    void isSinglePlayer() {
    }

    @Test
    void drawSoloActionToken() {
    }

    @Test
    void countLeaderCardInHand() {
    }

    @Test
    void calculateVictoryPoints() {
    }
}