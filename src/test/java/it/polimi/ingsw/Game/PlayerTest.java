package it.polimi.ingsw.Game;
import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import it.polimi.ingsw.Table.Decks.Leader.*;
import it.polimi.ingsw.Table.Market.Marbles.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the class: Player
 */
class PlayerTest {

    /**
     * This method tests setting the inkwell on a player
     */
    @Test
    void setInkwell(){
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            fail();
        }
        Player player = game.getPlayers().get(0);
        player.setInkwell();
        assertTrue(player.isInkwell());
    }

    /**
     * This method tests the getter of the parameter nickname
     */
    @Test
    void getNickname(){
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            fail();
        }
        Player player = game.getPlayers().get(0);
        assertEquals("Carlo", player.getNickname());
    }

    /**
     * This method tests getting what discount is active
     */
    @Test
    void getActiveDiscount(){
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            fail();
        }
        Player player = game.getPlayers().get(0);
        int numDiscounts = 0;
        for(Resource i : player.getActiveDiscount()){
            if(i != null){
                numDiscounts++;
            }
        }
        assertEquals(numDiscounts, 0);
        Type[] cost = {Type.GREEN, Type.BLUE};
        player.getCardsOnTable()[0] = new DiscountLeaderCard(3,Resource.COIN, cost);
        assertEquals(player.getActiveDiscount()[0], Resource.COIN);
    }

    /**
     * This method tests discarding a leader card
     */
    @Test
    void testDiscardLeaderCard(){
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            fail();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.selectThoCardsToKeep(1,2);
        } catch (PositionInvalidException | ActionNotAllowedException e) {
            fail();
        }
        try {
            player.discardLeaderCard(2);
        } catch (AlreadyDiscardedThisLeaderCardException | PositionInvalidException e) {
            fail();
        }
        assertSame(1, player.getPersonalBoard().getFaithTrack().getFaithMarker());
        assertNull(player.getCardsInHand()[1]);
    }

    /**
     * This method tests if you can play a leader card
     */
    @Test
    void canYouPlayAtLeastALeaderCard(){
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            fail();
        }
        Player player = game.getPlayers().get(0);
        assertFalse(player.canYouPlayAtLeastALeaderCard());
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.COIN,100);
            player.getPersonalBoard().getStrongBox().add(Resource.SERVANT,100);
            player.getPersonalBoard().getStrongBox().add(Resource.SHIELD,100);
            player.getPersonalBoard().getStrongBox().add(Resource.STONE,100);
        } catch (NotAResourceForStrongBoxException e) {
            fail();
        }
        player.getCardsInHand()[0] = new ExtraStorageLeaderCard(3, Resource.COIN, Resource.SERVANT);
        player.getCardsInHand()[1] = new ExtraStorageLeaderCard(3, Resource.COIN, Resource.SERVANT);
        assertTrue(player.canYouPlayAtLeastALeaderCard());
    }

    /**
     * This method tests playing a leader card
     */
    @Test
    void playLeaderCard(){
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            fail();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.COIN,100);
            player.getPersonalBoard().getStrongBox().add(Resource.SERVANT,100);
            player.getPersonalBoard().getStrongBox().add(Resource.SHIELD,100);
            player.getPersonalBoard().getStrongBox().add(Resource.STONE,100);
        } catch (NotAResourceForStrongBoxException e) {
            fail();
        }
        LeaderCard leaderCard = new ExtraStorageLeaderCard(3, Resource.COIN, Resource.SERVANT);
        player.getCardsInHand()[0] = leaderCard;
        try {
            player.playLeaderCard(1);
        } catch (NotSatisfiedRequirementsForThisLeaderCardException | PositionInvalidException e) {
            fail();
        }
        assertSame(leaderCard, player.getCardsOnTable()[0]);
    }

    /**
     * This method tests taking resources from the market
     */
    @Test
    void testTakeResourcesFromTheMarket(){
        Game game = new Game(1);
        try {
            game.addPlayer("Andrea");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            fail();
        }
        Player player = game.getPlayers().get(0);
        Type[] cost = {Type.PURPLE, Type.BLUE};
        player.getCardsOnTable()[0] = new WhiteMarbleLeaderCard(3, cost, new Servant());
        assertFalse(player.resourceToAdd());
        try {
            player.takeResourcesFromTheMarket(RowColumn.ROW, 2);
        } catch (PositionInvalidException | NullEnumException | NoWhiteMarbleLeaderCardException e) {
            fail();
        }
        assertTrue(player.resourceToAdd());
    }

    /**
     * This method tests moving resources in the warehouse depots
     */
    @Test
    void testMoveResourcesInWarehouseDepots(){
        Game game = new Game(1);
        try {
            game.addPlayer("Aldo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            fail();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.getPersonalBoard().getWarehouseDepots().addResource(Resource.COIN, 0);
            player.getPersonalBoard().getWarehouseDepots().addResource(Resource.SERVANT, 1);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException | IndexOutOfWarehouseDepotsException e) {
            fail();
        }
        try {
            player.selectAWarehouseDepotsSlot(0);
        } catch (PositionInvalidException e) {
            fail();
        }
        try {
            player.moveResourcesInWarehouseDepots(1);
        } catch (NotAdmittedMovementException | IndexOutOfWarehouseDepotsException e) {
            fail();
        }
        assertSame(player.getPersonalBoard().getWarehouseDepots().getResource()[0], Resource.SERVANT);
        assertSame(player.getPersonalBoard().getWarehouseDepots().getResource()[1], Resource.COIN);
        try {
            player.moveResourcesInWarehouseDepots(5);
        } catch (NotAdmittedMovementException | IndexOutOfWarehouseDepotsException e) {
            fail();
        }
        assertSame(player.getPersonalBoard().getWarehouseDepots().getResource()[5], Resource.SERVANT);
        assertNull(player.getPersonalBoard().getWarehouseDepots().getResource()[0]);
    }

    /**
     * This method tests moving resources from WarehouseDepots to ExtraStorageLeaderCard
     */
    @Test
    void moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard(){
        Game game = new Game(1);
        try {
            game.addPlayer("Aldo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            fail();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.getPersonalBoard().getWarehouseDepots().addResource(Resource.COIN, 0);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException | IndexOutOfWarehouseDepotsException e) {
            fail();
        }
        try {
            player.selectAWarehouseDepotsSlot(0);
        } catch (PositionInvalidException e) {
           fail();
        }
        player.getCardsOnTable()[0] = new ExtraStorageLeaderCard(3, Resource.COIN, Resource.COIN);
        try {
            player.moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard(1);
        } catch (PositionInvalidException | NotAnExtraStorageLeaderCardException | EmptySlotYetException | OccupiedSlotExtraStorageLeaderCardException | DifferentStorageException e) {
           fail();
        }
        assertNull(player.getPersonalBoard().getWarehouseDepots().getResource()[0]);
        assertSame(1, ((ExtraStorageLeaderCard)(player.getCardsOnTable()[0])).occupiedResources());
    }

    /**
     * This method tests moving resources from ExtraStorageLeaderCard to WarehouseDepots
     */
    @Test
    void moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard() {
        Game game = new Game(1);
        try {
            game.addPlayer("Aldo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.selectAWarehouseDepotsSlot(0);
        } catch (PositionInvalidException e) {
           fail();
        }
        ExtraStorageLeaderCard extraStorageLeaderCard = new ExtraStorageLeaderCard(3, Resource.COIN, Resource.COIN);
        try {
            extraStorageLeaderCard.addResource();
        } catch (OccupiedSlotExtraStorageLeaderCardException e) {
           fail();
        }
        player.getCardsOnTable()[0] = extraStorageLeaderCard;
        try {
            player.moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(1);
        } catch (PositionInvalidException | NotAnExtraStorageLeaderCardException | PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException | EmptySlotExtraStorageLeaderCardException | IndexOutOfWarehouseDepotsException e) {
           fail();
        }
        assertSame(0, ((ExtraStorageLeaderCard)(player.getCardsOnTable()[0])).occupiedResources());
        assertSame(Resource.COIN, player.getPersonalBoard().getWarehouseDepots().getResource()[0]);
    }

    /**
     * This method tests taking resource from the market
     */
    @Test
    void resourceToAdd() {
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        assertFalse(player.resourceToAdd());
        try {
            player.takeResourcesFromTheMarket(RowColumn.ROW, 1);
        } catch (PositionInvalidException | NullEnumException | NoWhiteMarbleLeaderCardException e) {
           fail();
        }
        assertTrue(player.resourceToAdd());
    }

    /**
     * This method tests taking resource from the market
     */
    @Test
    void whichResourceToAdd() {
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        Marble marble = game.getTable().getMarket().getMarketTray()[0][0];
        try {
            player.takeResourcesFromTheMarket(RowColumn.ROW, 1);
        } catch (PositionInvalidException | NullEnumException | NoWhiteMarbleLeaderCardException e) {
           fail();
        }
        if(marble instanceof Coin){
            try {
                assertSame(marble, player.whichResourceToAdd());
            } catch (NoMarbleToAddFromTheMarketException e) {
               fail();
            }
        }
        if(marble instanceof Servant){
            try {
                assertSame(marble, player.whichResourceToAdd());
            } catch (NoMarbleToAddFromTheMarketException e) {
               fail();
            }
        }
        if(marble instanceof Shield){
            try {
                assertSame(marble, player.whichResourceToAdd());
            } catch (NoMarbleToAddFromTheMarketException e) {
               fail();
            }
        }
        if(marble instanceof Stone){
            try {
                assertSame(marble, player.whichResourceToAdd());
            } catch (NoMarbleToAddFromTheMarketException e) {
               fail();
            }
        }
    }

    /**
     * This method tests adding resources taken from the market
     */
    @Test
    void addResource() {
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.takeResourcesFromTheMarket(RowColumn.ROW, 1);
        } catch (PositionInvalidException | NullEnumException | NoWhiteMarbleLeaderCardException e) {
           fail();
        }
        try {
            player.addResource(LeaderWarehouse.WAREHOUSEDEPOTS, 0);
        } catch (NoResourceToAddException | DifferentStorageException | OccupiedSlotExtraStorageLeaderCardException | PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException | UnexpectedWhiteMarbleException | UnexpectedFaithMarbleException | IndexOutOfWarehouseDepotsException e) {
           fail();
        }
        assertNotNull(player.getPersonalBoard().getWarehouseDepots().getResource()[0]);
    }

    /**
     * This method tests changing a white marble
     */
    @Test
    void changeWhiteMarbleWith() {
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        int rowWithWhiteMarble = 0;
        Marble[][] market = game.getTable().getMarket().getMarketTray();
        int countRow = 0;
        for(Marble[] i : market){
            boolean foundWhiteMarble = false;
            for(Marble j : i){
                if (j instanceof White) {
                    foundWhiteMarble = true;
                    break;
                }
            }
            if(foundWhiteMarble){
                rowWithWhiteMarble = countRow;
            }
            countRow++;
        }
        Type[] cost = {Type.GREEN, Type.BLUE};
        player.getCardsOnTable()[0] = new WhiteMarbleLeaderCard(3, cost, new Coin());
        player.getCardsOnTable()[1] = new WhiteMarbleLeaderCard(3, cost, new Servant());
        try {
            player.takeResourcesFromTheMarket(RowColumn.ROW, rowWithWhiteMarble+1);
        } catch (PositionInvalidException | NullEnumException | NoWhiteMarbleLeaderCardException e) {
           fail();
        }
        while(true){
            try {
                if (player.whichResourceToAdd() instanceof White) break;
            } catch (NoMarbleToAddFromTheMarketException e) {
               fail();
            }
            Resource correspondent = Resource.COIN;
            try {
                if(player.whichResourceToAdd() instanceof Servant){
                    correspondent = Resource.SERVANT;
                }
                if(player.whichResourceToAdd() instanceof Shield){
                    correspondent = Resource.SHIELD;
                }
                if(player.whichResourceToAdd() instanceof Stone){
                    correspondent = Resource.STONE;
                }
            } catch (NoMarbleToAddFromTheMarketException e) {
               fail();
            }
            player.getCardsOnTable()[0] = new ExtraStorageLeaderCard(3, Resource.COIN, correspondent);
            try {
                player.addResource(LeaderWarehouse.LEADERCARD,1);
            } catch (NoResourceToAddException | DifferentStorageException | OccupiedSlotExtraStorageLeaderCardException | PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException | UnexpectedWhiteMarbleException | UnexpectedFaithMarbleException | IndexOutOfWarehouseDepotsException e) {
               fail();
            }
        }
        try {
            assertTrue(player.whichResourceToAdd() instanceof White);
        } catch (NoMarbleToAddFromTheMarketException e) {
           fail();
        }
        try {
            player.changeWhiteMarbleWith(2);
        } catch (NoWhiteMarbleException | NoWhiteMarbleLeaderCardException | PositionInvalidException e) {
           fail();
        }
        try {
            assertTrue(player.whichResourceToAdd() instanceof Servant);
        } catch (NoMarbleToAddFromTheMarketException e) {
           fail();
        }
    }

    /**
     * This method tests the possibility of activating a power production
     */
    @Test
    void testCanYouActivateDefaultPowerProduction() {
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        assertFalse(player.canYouActivateAPowerProduction());
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.COIN, 2);
        } catch (NotAResourceForStrongBoxException e) {
           fail();
        }
        assertTrue(player.canYouActivateAPowerProduction());
    }

    /**
     * This method tests the production
     */
    @Test
    void testExamplePowerProduction() {
        Game game = new Game(1);
        try {
            game.addPlayer("Aldo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.getPersonalBoard().getWarehouseDepots().addResource(Resource.COIN, 0);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException | IndexOutOfWarehouseDepotsException e) {
           fail();
        }
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.SHIELD, 1);
        } catch (NotAResourceForStrongBoxException e) {
           fail();
        }
        player.selectDefaultProductionPower();
        try {
            player.startPayment();
        } catch (NotEnoughResourcesException | YouHaveNotSelectedAnyProductionException e) {
           fail();
        }
        try {
            player.payWithWarehouseDepots(0);
        } catch (WrongPaymentException | EmptySlotYetException | NoResourceToPayException e) {
           fail();
        }
        try {
            player.payWithStrongBox(Resource.SHIELD);
        } catch (WrongPaymentException | NotAResourceForStrongBoxException | NegativeResourceException | NoResourceToPayException e) {
           fail();
        }
        try {
            player.obtainGenericResource(Resource.SERVANT);
        } catch (NoGenericResourceToObtainException | NotAResourceForStrongBoxException e) {
           fail();
        }
        assertNull(player.getPersonalBoard().getWarehouseDepots().getResource()[0]);
        assertSame(player.getPersonalBoard().getStrongBox().getServant(), 1);
        assertSame(player.getPersonalBoard().getStrongBox().getShield(), 0);
    }

    /**
     * This method initializes a development card
     * @param level: level of the card
     * @return a development card, of type DevelopmentCard
     */
    private DevelopmentCard exampleDevelopmentCard(int level){
        Resource[] cost = {Resource.COIN, Resource.SERVANT, Resource.SHIELD};
        int[] costNumber = {1,2,3};
        Resource[] requirements = {Resource.COIN, Resource.SERVANT};
        int[] costRequirements = {2,1};
        Resource[] products = {Resource.COIN, Resource.FAITH};
        int[] costProducts = {2,2};
        return new DevelopmentCard(cost, costNumber, Type.BLUE, level, requirements, costRequirements, products, costProducts, 3);
    }

    /**
     * This method tests selecting a ProductionDevelopmentCard
     */
    @Test
    void selectProductionDevelopmentCard() {
        Game game = new Game(1);
        try {
            game.addPlayer("Aldo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.getPersonalBoard().getSlotsDevelopmentCards().addDevelopmentCard(1, exampleDevelopmentCard(1));
        } catch (PositionInvalidException | IndexOutOfSlotDevelopmentCardsException e) {
           fail();
        }
        try {
            player.selectProductionDevelopmentCard(1);
        } catch (PositionInvalidException | NoDevelopmentCardInThisPositionException e) {
           fail();
        }
        assertThrows(NotEnoughResourcesException.class, player::startPayment);
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.COIN, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.SHIELD, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.STONE, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.SERVANT, 100);
        } catch (NotAResourceForStrongBoxException e) {
           fail();
        }
        try {
            player.startPayment();
        } catch (NotEnoughResourcesException | YouHaveNotSelectedAnyProductionException e) {
           fail();
        }
    }

    /**
     * This method tests selecting a ProductionPowerLeaderCard
     */
    @Test
    void selectProductionPowerLeaderCard() {
        Game game = new Game(1);
        try {
            game.addPlayer("Aldo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        player.getCardsOnTable()[0] = new ProductionPowerLeaderCard(3, Type.BLUE, Resource.COIN);
        try {
            player.selectProductionPowerLeaderCard(1);
        } catch (NoProductionLeaderCardException | PositionInvalidException e) {
           fail();
        }
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.COIN, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.SHIELD, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.STONE, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.SERVANT, 100);
        } catch (NotAResourceForStrongBoxException e) {
           fail();
        }
        try {
            player.startPayment();
        } catch (NotEnoughResourcesException | YouHaveNotSelectedAnyProductionException e) {
           fail();
        }
    }

    /**
     * This method tests paying with ExtraStorageLeaderCard
     */
    @Test
    void payWithExtraStorageLeaderCard() {
        Game game = new Game(1);
        try {
            game.addPlayer("Aldo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        player.getCardsOnTable()[1] = new ProductionPowerLeaderCard(3, Type.BLUE, Resource.COIN);
        try {
            player.selectProductionPowerLeaderCard(2);
        } catch (NoProductionLeaderCardException | PositionInvalidException e) {
           fail();
        }
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.COIN, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.SHIELD, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.STONE, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.SERVANT, 100);
        } catch (NotAResourceForStrongBoxException e) {
           fail();
        }
        while(player.somethingToPay()){
            ExtraStorageLeaderCard extraStorageLeaderCard = null;
            try {
                extraStorageLeaderCard = new ExtraStorageLeaderCard(3, player.nextToPay(), player.nextToPay());
            } catch (NoResourceToPayException e) {
               fail();
            }
            player.getCardsOnTable()[0] = extraStorageLeaderCard;
            try {
                extraStorageLeaderCard.addResource();
            } catch (OccupiedSlotExtraStorageLeaderCardException e) {
               fail();
            }
            try {
                player.payWithExtraStorageLeaderCard(1);
            } catch (NotAnExtraStorageLeaderCardException | WrongPaymentException | EmptySlotExtraStorageLeaderCardException | NoResourceToPayException | PositionInvalidException e) {
               fail();
            }
        }
    }

    /**
     * This method tests getting generic resources
     */
    @Test
    void genericResourcesToObtain() {
        Game game = new Game(1);
        try {
            game.addPlayer("Aldo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.COIN, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.SHIELD, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.STONE, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.SERVANT, 100);
        } catch (NotAResourceForStrongBoxException e) {
           fail();
        }
        ProductionPowerLeaderCard productionPowerLeaderCard = new ProductionPowerLeaderCard(3, Type.BLUE, Resource.COIN);
        player.getCardsOnTable()[0] = productionPowerLeaderCard;
        try {
            player.selectProductionPowerLeaderCard(1);
        } catch (NoProductionLeaderCardException | PositionInvalidException e) {
           fail();
        }
        try {
            player.startPayment();
        } catch (NotEnoughResourcesException | YouHaveNotSelectedAnyProductionException e) {
           fail();
        }
        assertTrue(player.genericResourcesToObtain());
    }

    /**
     * This method tests getting generic resources
     */
    @Test
    void obtainGenericResource() {
        Game game = new Game(1);
        try {
            game.addPlayer("Aldo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.COIN, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.SHIELD, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.STONE, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.SERVANT, 100);
        } catch (NotAResourceForStrongBoxException e) {
           fail();
        }
        ProductionPowerLeaderCard productionPowerLeaderCard = new ProductionPowerLeaderCard(3, Type.BLUE, Resource.COIN);
        player.getCardsOnTable()[0] = productionPowerLeaderCard;
        try {
            player.selectProductionPowerLeaderCard(1);
        } catch (NoProductionLeaderCardException | PositionInvalidException e) {
           fail();
        }
        try {
            player.startPayment();
        } catch (NotEnoughResourcesException | YouHaveNotSelectedAnyProductionException e) {
           fail();
        }
        while(player.somethingToPay()){
            try {
                player.payWithStrongBox(player.nextToPay());
            } catch (WrongPaymentException | NegativeResourceException | NotAResourceForStrongBoxException | NoResourceToPayException e) {
               fail();
            }
        }
        while(player.genericResourcesToObtain()){
            try {
                player.obtainGenericResource(Resource.COIN);
            } catch (NoGenericResourceToObtainException | NotAResourceForStrongBoxException e) {
               fail();
            }
        }
    }

    /**
     * This method tests buying a DevelopmentCard
     */
    @Test
    void canYouBuyADevelopmentCard() {
        Game game = new Game(1);
        try {
            game.addPlayer("Aldo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.COIN, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.SHIELD, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.STONE, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.SERVANT, 100);
        } catch (NotAResourceForStrongBoxException e) {
           fail();
        }
        assertTrue(player.canYouBuyADevelopmentCard());
    }

    /**
     * This method tests buying a DevelopmentCard
     */
    @Test
    void developmentCardToObtain() {
        Game game = new Game(1);
        try {
            game.addPlayer("Aldo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        assertFalse(player.developmentCardToObtain());
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.COIN, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.SHIELD, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.STONE, 100);
            player.getPersonalBoard().getStrongBox().add(Resource.SERVANT, 100);
        } catch (NotAResourceForStrongBoxException e) {
           fail();
        }
        try {
            player.buyADevelopmentCard(3,1);
        } catch (PositionInvalidException | NotAbleToBuyThisDevelopmentCardException | NotAbleToPlaceThisDevelopmentCardException | DrawnFromEmptyDeckException | SelectedADevelopmentCardYetException | IndexOutOfDevelopmentDeckException e) {
           fail();
        }
        assertTrue(player.developmentCardToObtain());
    }

    /**
     * This method tests obtaining a DevelopmentCard
     */
    @Test
    void obtainDevelopmentCard() {
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.COIN,100);
            player.getPersonalBoard().getStrongBox().add(Resource.SERVANT,100);
            player.getPersonalBoard().getStrongBox().add(Resource.SHIELD,100);
            player.getPersonalBoard().getStrongBox().add(Resource.STONE,100);
        } catch (NotAResourceForStrongBoxException e) {
           fail();
        }
        DevelopmentCard developmentCard = game.getTable().getDevelopmentDeck().visualize()[2][0];
        try {
            player.buyADevelopmentCard(3,1);
        } catch (PositionInvalidException | NotAbleToBuyThisDevelopmentCardException | NotAbleToPlaceThisDevelopmentCardException | DrawnFromEmptyDeckException | SelectedADevelopmentCardYetException | IndexOutOfDevelopmentDeckException e) {
           fail();
        }
        while(player.somethingToPay()){
            try {
                player.payWithStrongBox(player.nextToPay());
            } catch (WrongPaymentException | NegativeResourceException | NotAResourceForStrongBoxException | NoResourceToPayException e) {
               fail();
            }
        }
        try {
            player.placeDevelopmentCard(1);
        } catch (NoDevelopmentCardToObtainException | PositionInvalidException | IndexOutOfSlotDevelopmentCardsException e) {
           fail();
        }
        assertSame(developmentCard, player.getPersonalBoard().getSlotsDevelopmentCards().getActiveCards()[0]);
    }

    /**
     * This method tests setting the player in the single player mode
     */
    @Test
    void isSinglePlayer() {
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        player.isSinglePlayer();
        assertNotNull(player.getPersonalBoard().getLorenzoTrack());
    }

    /**
     * This method tests drawing a solo action token
     */
    @Test
    void drawSoloActionToken() {
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        player.isSinglePlayer();
        player.drawSoloActionToken();
    }

    /**
     * This method tests counting LeaderCard in hand
     */
    @Test
    void countLeaderCardInHand() {
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        try {
            player.selectThoCardsToKeep(1,2);
        } catch (PositionInvalidException | ActionNotAllowedException e) {
           fail();
        }
        assertSame(2, player.countLeaderCardInHand());
        player.getCardsInHand()[0] = null;
        assertSame(1, player.countLeaderCardInHand());
        player.getCardsInHand()[1] = null;
        assertSame(0, player.countLeaderCardInHand());
    }

    /**
     * This method tests calculating victory points
     */
    @Test
    void calculateVictoryPoints() {
        Game game = new Game(1);
        try {
            game.addPlayer("Carlo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
           fail();
        }
        Player player = game.getPlayers().get(0);
        assertSame(0, player.calculateVictoryPoints());
        try {
            player.getPersonalBoard().getStrongBox().add(Resource.COIN,100);
            player.getPersonalBoard().getStrongBox().add(Resource.SERVANT,100);
            player.getPersonalBoard().getStrongBox().add(Resource.SHIELD,100);
            player.getPersonalBoard().getStrongBox().add(Resource.STONE,100);
        } catch (NotAResourceForStrongBoxException e) {
           fail();
        }
        assertSame(80, player.calculateVictoryPoints());
        player.getCardsOnTable()[0] = new ExtraStorageLeaderCard(3, Resource.COIN, Resource.COIN);
        assertSame(83, player.calculateVictoryPoints());
        player.getPersonalBoard().getFaithTrack().goOn(3);
        assertSame(84, player.calculateVictoryPoints());
        player.getPersonalBoard().getFaithTrack().goOn(2);
        assertSame(84, player.calculateVictoryPoints());
        player.getPersonalBoard().getFaithTrack().goOn(1);
        assertSame(85, player.calculateVictoryPoints());
        player.getPersonalBoard().getFaithTrack().goOn(2);
        assertSame(87, player.calculateVictoryPoints());
        try {
            player.getPersonalBoard().getSlotsDevelopmentCards().addDevelopmentCard(3, exampleDevelopmentCard(1));
        } catch (PositionInvalidException | IndexOutOfSlotDevelopmentCardsException e) {
           fail();
        }
        assertSame(90, player.calculateVictoryPoints());
        try {
            player.getPersonalBoard().getSlotsDevelopmentCards().addDevelopmentCard(3, exampleDevelopmentCard(2));
        } catch (PositionInvalidException | IndexOutOfSlotDevelopmentCardsException e) {
           fail();
        }
        assertSame(93, player.calculateVictoryPoints());
    }

}