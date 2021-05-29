package it.polimi.ingsw.Messages;
import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Enums.NormalAction;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ErrorMessages.AlreadyDiscardedPositionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.AlreadyEmptyErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.AlreadySelectedSomethingErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.DifferentResourceAlreadyPresentErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.DifferentStorageTypeErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.EmptyDeckErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.FaithMarbleErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameEndedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GenericResourceErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidEnumErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidMovementErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidPositionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidSelectionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.MissingResourceErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoCardDiscardErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoCardObtainableErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoCardsPlayErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoDevelopmentCardErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoPLCErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoResourceAErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoSelectedPowersErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoWhiteMarbleErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotEnoughRErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotEnoughResourcesErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotEsErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotStrongboxErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotYourTurnErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.OccupiedSlotLCErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.OccupiedSlotWDErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.RequirementsErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.TypoErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.WhiteMarbleErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.WrongResourceErrorMessage;
import it.polimi.ingsw.Messages.ForwardMessages.*;
import it.polimi.ingsw.Messages.GameMessages.AddResourceToGameMessage;
import it.polimi.ingsw.Messages.GameMessages.BuyDevelopmentCardGameMessage;
import it.polimi.ingsw.Messages.GameMessages.ChangeWhiteMarbleWithGameMessage;
import it.polimi.ingsw.Messages.GameMessages.ChooseDiscardLeaderCardGameMessage;
import it.polimi.ingsw.Messages.GameMessages.ChooseNoActionLeaderCardGameMessage;
import it.polimi.ingsw.Messages.GameMessages.ChoosePlayLeaderCardGameMessage;
import it.polimi.ingsw.Messages.GameMessages.DiscardLeaderCardGameMessage;
import it.polimi.ingsw.Messages.GameMessages.DrawSoloActionTokenGameMessage;
import it.polimi.ingsw.Messages.GameMessages.EndTurnGameMessage;
import it.polimi.ingsw.Messages.GameMessages.MoveResourceESLCToWEarehouseGameMessage;
import it.polimi.ingsw.Messages.GameMessages.MoveResourcesInWarehouseDepotsGameMessage;
import it.polimi.ingsw.Messages.GameMessages.MoveResourcesWarehouseToESLCGameMessage;
import it.polimi.ingsw.Messages.GameMessages.ObtainGenericResourceGameMessage;
import it.polimi.ingsw.Messages.GameMessages.PayWithExtraStorageLeaderCardGameMessage;
import it.polimi.ingsw.Messages.GameMessages.PayWithStrongboxGameMessage;
import it.polimi.ingsw.Messages.GameMessages.PayWithWarehouseDepotsGameMessage;
import it.polimi.ingsw.Messages.GameMessages.PlaceDevelopmentCardGameMessage;
import it.polimi.ingsw.Messages.GameMessages.PlayLeaderCardGameMessage;
import it.polimi.ingsw.Messages.GameMessages.SelectAWarehouseDepotsSlotGameMessage;
import it.polimi.ingsw.Messages.GameMessages.SelectNormalActionGameMessage;
import it.polimi.ingsw.Messages.GameMessages.SelectProductionDevelopmentCardGameMessage;
import it.polimi.ingsw.Messages.GameMessages.SelectProductionPowerLeaderCardGameMessage;
import it.polimi.ingsw.Messages.GameMessages.SelectTwoCardsToKeepGameMessage;
import it.polimi.ingsw.Messages.GameMessages.StartPaymentGameMessage;
import it.polimi.ingsw.Messages.GameMessages.TakeResourcesFromTheMarketGameMessage;
import it.polimi.ingsw.Messages.ServiceMessages.GameStartServiceMessage;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import it.polimi.ingsw.Table.Decks.Token.GreenActionToken;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for creation of messages
 */
public class MessagesTest {

    /**
     * This method tests creation of message NoCardDiscardErrorMessage
     */
    @Test
    void NoCardDiscardErrorMessageTest() {
        String messageString = "ERROR_NO_CARDS_DISCARD";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoCardDiscardErrorMessage);
        assertEquals("ERROR_NO_CARDS_DISCARD", message.getIdentifier());
    }

    /**
     * This method tests creation of message NotYourTurnErrorMessage
     */
    @Test
    void NotYourTurnErrorMessageTest() {
        String messageString = "ERROR_NOT_YOUR_TURN";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NotYourTurnErrorMessage);
        assertEquals("ERROR_NOT_YOUR_TURN", message.getIdentifier());
    }

    /**
     * This method tests creation of message InvalidActionErrorMessage
     */
    @Test
    void InvalidActionErrorMessageTest() {
        String messageString = "ERROR_INVALID_ACTION";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof InvalidActionErrorMessage);
        assertEquals("ERROR_INVALID_ACTION", message.getIdentifier());
    }

    /**
     * This method tests creation of message AlreadyDiscardedPositionErrorMessageTest
     */
    @Test
    void AlreadyDiscardedPositionErrorMessageTest() {
        String messageString = "ERROR_ALREADY_DISCARDED_POSITION";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof AlreadyDiscardedPositionErrorMessage);
        assertEquals("ERROR_ALREADY_DISCARDED_POSITION", message.getIdentifier());
    }

    /**
     * This method tests creation of message GameEndedErrorMessageTest
     */
    @Test
    void GameEndedErrorMessageTest() {
        String messageString = "ERROR_GAME_ENDED";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof GameEndedErrorMessage);
        assertEquals("ERROR_GAME_ENDED", message.getIdentifier());
    }

    /**
     * This method tests creation of message InvalidPositionErrorMessageTest
     */
    @Test
    void InvalidPositionErrorMessageTest() {
        String messageString = "ERROR_INVALID_POSITION";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof InvalidPositionErrorMessage);
        assertEquals("ERROR_INVALID_POSITION", message.getIdentifier());
    }

    /**
     * This method tests creation of message TypoErrorMessageTest
     */
    @Test
    void TypoErrorMessageTest() {
        String messageString = "ERROR_TYPO";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof TypoErrorMessage);
        assertEquals("ERROR_TYPO", message.getIdentifier());
    }

    /**
     * This method tests creation of message NoCardsPlayErrorMessageTest
     */
    @Test
    void NoCardsPlayErrorMessageTest() {
        String messageString = "ERROR_NO_CARDS_PLAY";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoCardsPlayErrorMessage);
        assertEquals("ERROR_NO_CARDS_PLAY", message.getIdentifier());
    }

    /**
     * This method tests creation of message RequirementsErrorMessageTest
     */
    @Test
    void RequirementsErrorMessageTest() {
        String messageString = "ERROR_REQUIREMENTS";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof RequirementsErrorMessage);
        assertEquals("ERROR_REQUIREMENTS", message.getIdentifier());
    }

    /**
     * This method tests creation of message InvalidEnumErrorMessageTest
     */
    @Test
    void InvalidEnumErrorMessageTest() {
        String messageString = "ERROR_INVALID_ENUM";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof InvalidEnumErrorMessage);
        assertEquals("ERROR_INVALID_ENUM", message.getIdentifier());
    }

    /**
     * This method tests creation of message NoResourceAErrorMessageTest
     */
    @Test
    void NoResourceAErrorMessageTest() {
        String messageString = "ERROR_NO_RESOURCE_A";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoResourceAErrorMessage);
        assertEquals("ERROR_NO_RESOURCE_A", message.getIdentifier());
    }

    /**
     * This method tests creation of message DifferentStorageTypeErrorMessageTest
     */
    @Test
    void DifferentStorageTypeErrorMessageTest() {
        String messageString = "ERROR_DIFFERENT_STORAGE_TYPE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof DifferentStorageTypeErrorMessage);
        assertEquals("ERROR_DIFFERENT_STORAGE_TYPE", message.getIdentifier());
    }

    /**
     * This method tests creation of message OccupiedSlotLCErrorMessageTest
     */
    @Test
    void OccupiedSlotLCErrorMessageTest() {
        String messageString = "ERROR_OCCUPIED_SLOT_LC";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof OccupiedSlotLCErrorMessage);
        assertEquals("ERROR_OCCUPIED_SLOT_LC", message.getIdentifier());
    }

    /**
     * This method tests creation of message OccupiedSlotWDErrorMessageTest
     */
    @Test
    void OccupiedSlotWDErrorMessageTest() {
        String messageString = "ERROR_OCCUPIED_SLOT_WD";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof OccupiedSlotWDErrorMessage);
        assertEquals("ERROR_OCCUPIED_SLOT_WD", message.getIdentifier());
    }

    /**
     * This method tests creation of message DifferentResourceAlreadyPresentErrorMessageTest
     */
    @Test
    void DifferentResourceAlreadyPresentErrorMessageTest() {
        String messageString = "ERROR_DIFFERENT_RESOURCE_ALREADY_PRESENT";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof DifferentResourceAlreadyPresentErrorMessage);
        assertEquals("ERROR_DIFFERENT_RESOURCE_ALREADY_PRESENT", message.getIdentifier());
    }

    /**
     * This method tests creation of message WhiteMarbleErrorMessageTest
     */
    @Test
    void WhiteMarbleErrorMessageTest() {
        String messageString = "ERROR_WHITE_MARBLE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof WhiteMarbleErrorMessage);
        assertEquals("ERROR_WHITE_MARBLE", message.getIdentifier());
    }

    /**
     * This method tests creation of message FaithMarbleErrorMessageTest
     */
    @Test
    void FaithMarbleErrorMessageTest() {
        String messageString = "ERROR_FAITH_MARBLE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof FaithMarbleErrorMessage);
        assertEquals("ERROR_FAITH_MARBLE", message.getIdentifier());
    }

    /**
     * This method tests creation of message NoWhiteMarbleErrorMessageTest
     */
    @Test
    void NoWhiteMarbleErrorMessageTest() {
        String messageString = "ERROR_NO_WHITE_MARBLE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoWhiteMarbleErrorMessage);
        assertEquals("ERROR_NO_WHITE_MARBLE", message.getIdentifier());
    }

    /**
     * This method tests creation of message AlreadySelectedSomethingErrorMessageTest
     */
    @Test
    void AlreadySelectedSomethingErrorMessageTest() {
        String messageString = "ERROR_ALREADY_SELECTED_SOMETHING";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof AlreadySelectedSomethingErrorMessage);
        assertEquals("ERROR_ALREADY_SELECTED_SOMETHING", message.getIdentifier());
    }

    /**
     * This method tests creation of message NotEnoughResourcesErrorMessageTest
     */
    @Test
    void NotEnoughResourcesErrorMessageTest() {
        String messageString = "ERROR_NOT_ENOUGH_RESOURCES";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NotEnoughResourcesErrorMessage);
        assertEquals("ERROR_NOT_ENOUGH_RESOURCES", message.getIdentifier());
    }

    /**
     * This method tests creation of message EmptyDeckErrorMessageTest
     */
    @Test
    void EmptyDeckErrorMessageTest() {
        String messageString = "ERROR_EMPTY_DECK";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof EmptyDeckErrorMessage);
        assertEquals("ERROR_EMPTY_DECK", message.getIdentifier());
    }

    /**
     * This method tests creation of message InvalidSelectionErrorMessageTest
     */
    @Test
    void InvalidSelectionErrorMessageTest() {
        String messageString = "ERROR_INVALID_SELECTION";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof InvalidSelectionErrorMessage);
        assertEquals("ERROR_INVALID_SELECTION", message.getIdentifier());
    }

    /**
     * This method tests creation of message WrongResourceErrorMessageTest
     */
    @Test
    void WrongResourceErrorMessageTest() {
        String messageString = "ERROR_WRONG_RESOURCE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof WrongResourceErrorMessage);
        assertEquals("ERROR_WRONG_RESOURCE", message.getIdentifier());
    }

    /**
     * This method tests creation of message MissingResourceErrorMessageTest
     */
    @Test
    void MissingResourceErrorMessageTest() {
        String messageString = "ERROR_MISSING_RESOURCE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof MissingResourceErrorMessage);
        assertEquals("ERROR_MISSING_RESOURCE", message.getIdentifier());
    }

    /**
     * This method tests creation of message NotStrongboxErrorMessageTest
     */
    @Test
    void NotStrongboxErrorMessageTest() {
        String messageString = "ERROR_NOT_STRONGBOX";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NotStrongboxErrorMessage);
        assertEquals("ERROR_NOT_STRONGBOX", message.getIdentifier());
    }

    /**
     * This method tests creation of message AlreadyEmptyErrorMessageTest
     */
    @Test
    void AlreadyEmptyErrorMessageTest() {
        String messageString = "ERROR_ALREADY_EMPTY";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof AlreadyEmptyErrorMessage);
        assertEquals("ERROR_ALREADY_EMPTY", message.getIdentifier());
    }

    /**
     * This method tests creation of message NotEsErrorMessageTest
     */
    @Test
    void NotEsErrorMessageTest() {
        String messageString = "ERROR_NOT_ES";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NotEsErrorMessage);
        assertEquals("ERROR_NOT_ES", message.getIdentifier());
    }

    /**
     * This method tests creation of message NoCardObtainableErrorMessageTest
     */
    @Test
    void NoCardObtainableErrorMessageTest() {
        String messageString = "ERROR_NO_CARD_OBTAINABLE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoCardObtainableErrorMessage);
        assertEquals("ERROR_NO_CARD_OBTAINABLE", message.getIdentifier());
    }

    /**
     * This method tests creation of message NoDevelopmentCardErrorMessageTest
     */
    @Test
    void NoDevelopmentCardErrorMessageTest() {
        String messageString = "ERROR_NO_DEVELOPMENT_CARD";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoDevelopmentCardErrorMessage);
        assertEquals("ERROR_NO_DEVELOPMENT_CARD", message.getIdentifier());
    }

    /**
     * This method tests creation of message NoPLCErrorMessageTest
     */
    @Test
    void NoPLCErrorMessageTest() {
        String messageString = "ERROR_NO_PLC";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoPLCErrorMessage);
        assertEquals("ERROR_NO_PLC", message.getIdentifier());
    }

    /**
     * This method tests creation of message GenericResourceErrorMessageTest
     */
    @Test
    void GenericResourceErrorMessageTest() {
        String messageString = "ERROR_GENERIC_RESOURCE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof GenericResourceErrorMessage);
        assertEquals("ERROR_GENERIC_RESOURCE", message.getIdentifier());
    }

    /**
     * This method tests creation of message NotEnoughRErrorMessageTest
     */
    @Test
    void NotEnoughRErrorMessageTest() {
        String messageString = "ERROR_NOT_ENOUGH_R";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NotEnoughRErrorMessage);
        assertEquals("ERROR_NOT_ENOUGH_R", message.getIdentifier());
    }

    /**
     * This method tests creation of message NoSelectedPowersErrorMessageTest
     */
    @Test
    void NoSelectedPowersErrorMessageTest() {
        String messageString = "ERROR_NO_SELECTED_POWERS";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoSelectedPowersErrorMessage);
        assertEquals("ERROR_NO_SELECTED_POWERS", message.getIdentifier());
    }

    /**
     * This method tests creation of message InvalidMovementErrorMessageTest
     */
    @Test
    void InvalidMovementErrorMessageTest() {
        String messageString = "ERROR_INVALID_MOVEMENT";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof InvalidMovementErrorMessage);
        assertEquals("ERROR_INVALID_MOVEMENT", message.getIdentifier());
    }

    /**
     * This method tests creation of message SelectNormalActionGameMessage
     */
    @Test
    void SelectNormalActionGameMessageTest() {
        String messageString = "SELECT_NORMAL_ACTION" + " " + NormalAction.ACTIVATE_PRODUCTION;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof SelectNormalActionGameMessage);
        assertEquals("SELECT_NORMAL_ACTION", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message TransitionForwardMessageTest
     */
    @Test
    void TransitionForwardMessageTest() {
        String messageString = "TRANSITION_MESSAGE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof TransitionForwardMessage);
        assertEquals("TRANSITION_MESSAGE", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message ConfirmedActionMessageTest
     */
    @Test
    void ConfirmedActionMessageTest() {
        String messageString = "ConfirmedActionMessage";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ConfirmedActionMessage);
    }

    /**
     * This method tests creation of message ShowCurrentBoardMessageTest
     */
    @Test
    void ShowCurrentBoardMessageTest() {
        String messageString = "SHOW_CURRENT_BOARD";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ShowCurrentBoardMessage);
        assertEquals("SHOW_CURRENT_BOARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message ShowCardMarketMessageTest
     */
    @Test
    void ShowCardMarketMessageTest() {
        String messageString = "SHOW_CARD_MARKET";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ShowCardMarketMessage);
        assertEquals("SHOW_CARD_MARKET", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message UpdateMarketForwardMessageTest
     */
    @Test
    void UpdateMarketForwardMessageTest() {
        String messageString = "UPDATE_MARKET" + " " + "nickname" + " " + RowColumn.COLUMN + " " + 2;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof UpdateMarketForwardMessage);
        assertEquals("UPDATE_MARKET", message.getIdentifier());
        assertEquals(message.toString(), messageString);

    }

    /**
     * This method tests creation of message UpdateDevelopmentCardForwardMessageTest
     */
    @Test
    void UpdateDevelopmentCardForwardMessageTest() {
        String messageString = "UPDATE_DEVELOPMENT_CARD" + " " + 1 + " " + 2;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof UpdateDevelopmentCardForwardMessage);
        assertEquals("UPDATE_DEVELOPMENT_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message UpdateSoloActionTokenMessage
     */
    @Test
    void UpdateSoloActionTokenMessage() {
        String messageString = "UPDATE_SOLO_ACTION_TOKEN" + " " + new GreenActionToken();
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof UpdateSoloActionTokenMessage);
        assertEquals("UPDATE_SOLO_ACTION_TOKEN", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message AdvanceFaithTrackForwardMessageTest
     */
    @Test
    void AdvanceFaithTrackForwardMessageTest() {
        String messageString = "ADVANCE_FAITH_TRACK" + " " + "nickname";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof AdvanceFaithTrackForwardMessage);
        assertEquals("ADVANCE_FAITH_TRACK", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message DiscardedLeaderCardForwardMessageTest
     */
    @Test
    void DiscardedLeaderCardForwardMessageTest() {
        String messageString = "DISCARDED_LEADER_CARD" + " " + "nickname" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof DiscardedLeaderCardForwardMessage);
        assertEquals("DISCARDED_LEADER_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message PlayedLeaderCardForwardMessageTest
     */
    @Test
    void PlayedLeaderCardForwardMessageTest() {
        String messageString = "PLAYED_LEADER_CARD" + " " + "nickname" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PlayedLeaderCardForwardMessage);
        assertEquals("PLAYED_LEADER_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message AddedResourceToForwardMessageTest
     */
    @Test
    void AddedResourceToForwardMessageTest() {
        String messageString = "ADDED_RESOURCE_TO" + " " + "nickname" + " " + LeaderWarehouse.DISCARD + " " + Resource.COIN + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof AddedResourceToForwardMessage);
        assertEquals("ADDED_RESOURCE_TO", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message PlacedDevelopmentCardForwardMessageTest
     */
    @Test
    void PlacedDevelopmentCardForwardMessageTest() {
        Resource[] cost = {Resource.COIN, Resource.SERVANT, Resource.SHIELD};
        int[] costNumber = {1, 2, 3};
        Resource[] requirements = {Resource.COIN};
        int[] costRequirements = {3};
        Resource[] products = {Resource.FAITH, Resource.SERVANT};
        int[] costProducts = {2, 1};
        DevelopmentCard developmentCard = new DevelopmentCard(cost, costNumber, Type.GREEN, 2,
                requirements, costRequirements, products, costProducts, 5);
        String messageString = "PLACED_DEVELOPMENT_CARD" + " " + "nickname" + " " + 1 + " " + developmentCard.export();
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PlacedDevelopmentCardForwardMessage);
        assertEquals("PLACED_DEVELOPMENT_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message UpdateObtainedMultipleResourceForwardMessageTest
     */
    @Test
    void UpdateObtainedMultipleResourceForwardMessageTest() {
        String messageString = "UPDATE_MULTIPLE_RESOURCES" + " " + "nickname" + " " + 1 + " " + 1 + " " + 1 + " " + 1 + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof UpdateObtainedMultipleResourceForwardMessage);
        assertEquals("UPDATE_MULTIPLE_RESOURCES", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message ObtainedGenericResourceForwardMessageTest
     */
    @Test
    void ObtainedGenericResourceForwardMessageTest() {
        String messageString = "OBTAINED_GENERIC_RESOURCE" + " " + "nickname" + " " + Resource.COIN;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ObtainedGenericResourceForwardMessage);
        assertEquals("OBTAINED_GENERIC_RESOURCE", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message PayedWithExtraStorageLeaderCardForwardMessageTest
     */
    @Test
    void PayedWithExtraStorageLeaderCardForwardMessageTest() {
        String messageString = "PAYED_WITH_EXTRA_STORAGE_LEADER_CARD" + " " + "nickname" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PayedWithExtraStorageLeaderCardForwardMessage);
        assertEquals("PAYED_WITH_EXTRA_STORAGE_LEADER_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message PayedWithStrongboxForwardMessage
     */
    @Test
    void PayedWithStrongboxForwardMessageTest() {
        String messageString = "PAYED_WITH_STRONGBOX" + " " + "nickname" + " " + Resource.SERVANT;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PayedWithStrongboxForwardMessage);
        assertEquals("PAYED_WITH_STRONGBOX", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message PayedWithWarehouseDepotsForwardMessageTest
     */
    @Test
    void PayedWithWarehouseDepotsForwardMessageTest() {
        String messageString = "PAYED_WITH_WAREHOUSE_DEPOTS" + " " + "nickname" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PayedWithWarehouseDepotsForwardMessage);
        assertEquals("PAYED_WITH_WAREHOUSE_DEPOTS", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message MovedResourcesInWarehouseDepotsForwardMessageTest
     */
    @Test
    void MovedResourcesInWarehouseDepotsForwardMessageTest() {
        String messageString = "MOVED_RESOURCES_IN_WAREHOUSE_DEPOTS" + " " + "nickname" + " " + 1 + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof MovedResourcesInWarehouseDepotsForwardMessage);
        assertEquals("MOVED_RESOURCES_IN_WAREHOUSE_DEPOTS", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message MovedResourceESLCToWarehouseForwardMessageTest
     */
    @Test
    void MovedResourceESLCToWarehouseForwardMessageTest() {
        String messageString = "MOVED_RESOURCE_ES_LC_TO_WAREHOUSE" + " " + "nickname" + " " + 1 + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof MovedResourceESLCToWarehouseForwardMessage);
        assertEquals("MOVED_RESOURCE_ES_LC_TO_WAREHOUSE", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message SelectedTwoCardsToKeepForwardMessageTest
     */
    @Test
    void SelectedTwoCardsToKeepForwardMessageTest() {
        String messageString = "SELECTED_TWO_CARDS_TO_KEEP" + " " + "nickname" + " " + 1 + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof SelectedTwoCardsToKeepForwardMessage);
        assertEquals("SELECTED_TWO_CARDS_TO_KEEP", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message ChooseDiscardLeaderCardGameMessageTest
     */
    @Test
    void ChooseDiscardLeaderCardGameMessageTest() {
        String messageString = "CHOOSE_DISCARD_LEADER_CARD";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ChooseDiscardLeaderCardGameMessage);
        assertEquals("CHOOSE_DISCARD_LEADER_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message DiscardLeaderCardGameMessageTest
     */
    @Test
    void DiscardLeaderCardGameMessageTest() {
        String messageString = "DISCARD_LEADER_CARD" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof DiscardLeaderCardGameMessage);
        assertEquals("DISCARD_LEADER_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message ChoosePlayLeaderCardGameMessageTest
     */
    @Test
    void ChoosePlayLeaderCardGameMessageTest() {
        String messageString = "CHOOSE_PLAY_LEADER_CARD";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ChoosePlayLeaderCardGameMessage);
        assertEquals("CHOOSE_PLAY_LEADER_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message PlayLeaderCardGameMessageTest
     */
    @Test
    void PlayLeaderCardGameMessageTest() {
        String messageString = "PLAY_LEADER_CARD" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PlayLeaderCardGameMessage);
        assertEquals("PLAY_LEADER_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message ChooseNoActionLeaderCardGameMessageTest
     */
    @Test
    void ChooseNoActionLeaderCardGameMessageTest() {
        String messageString = "CHOOSE_NO_ACTION_LEADER_CARD";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ChooseNoActionLeaderCardGameMessage);
        assertEquals("CHOOSE_NO_ACTION_LEADER_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message EndTurnGameMessageTest
     */
    @Test
    void EndTurnGameMessageTest() {
        String messageString = "END_TURN";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof EndTurnGameMessage);
        assertEquals("END_TURN", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message TakeResourcesFromTheMarketGameMessageTest
     */
    @Test
    void TakeResourcesFromTheMarketGameMessageTest() {
        String messageString = "TAKE_RESOURCES_FROM_THE_MARKET" + " " + RowColumn.COLUMN + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof TakeResourcesFromTheMarketGameMessage);
        assertEquals("TAKE_RESOURCES_FROM_THE_MARKET", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message ChangeWhiteMarbleWithGameMessageTest
     */
    @Test
    void ChangeWhiteMarbleWithGameMessageTest() {
        String messageString = "CHANGE_WHITE_MARBLE_WITH" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ChangeWhiteMarbleWithGameMessage);
        assertEquals("CHANGE_WHITE_MARBLE_WITH", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message BuyDevelopmentCardGameMessageTest
     */
    @Test
    void BuyDevelopmentCardGameMessageTest() {
        String messageString = "BUY_DEVELOPMENT_CARD" + " " + 1 + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof BuyDevelopmentCardGameMessage);
        assertEquals("BUY_DEVELOPMENT_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message PayWithStrongboxGameMessageTest
     */
    @Test
    void PayWithStrongboxGameMessageTest() {
        String messageString = "PAY_WITH_STRONGBOX" + " " + Resource.COIN;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PayWithStrongboxGameMessage);
        assertEquals("PAY_WITH_STRONGBOX", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message PayWithWarehouseDepotsGameMessageTest
     */
    @Test
    void PayWithWarehouseDepotsGameMessageTest() {
        String messageString = "PAY_WITH_WAREHOUSE_DEPOTS" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PayWithWarehouseDepotsGameMessage);
        assertEquals("PAY_WITH_WAREHOUSE_DEPOTS", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message PayWithExtraStorageLeaderCardGameMessageTest
     */
    @Test
    void PayWithExtraStorageLeaderCardGameMessageTest() {
        String messageString = "PAY_WITH_EXTRA_STORAGE_LEADER_CARD" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PayWithExtraStorageLeaderCardGameMessage);
        assertEquals("PAY_WITH_EXTRA_STORAGE_LEADER_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message PlaceDevelopmentCardGameMessageTest
     */
    @Test
    void PlaceDevelopmentCardGameMessageTest() {
        String messageString = "PLACE_DEVELOPMENT_CARD" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PlaceDevelopmentCardGameMessage);
        assertEquals("PLACE_DEVELOPMENT_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message SelectProductionDevelopmentCardGameMessageTest
     */
    @Test
    void SelectProductionDevelopmentCardGameMessageTest() {
        String messageString = "SELECT_PRODUCTION_DEVELOPMENT_CARD" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof SelectProductionDevelopmentCardGameMessage);
        assertEquals("SELECT_PRODUCTION_DEVELOPMENT_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message SelectProductionPowerLeaderCardGameMessageTest
     */
    @Test
    void SelectProductionPowerLeaderCardGameMessageTest() {
        String messageString = "SELECT_PRODUCTION_POWER_LEADER_CARD" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof SelectProductionPowerLeaderCardGameMessage);
        assertEquals("SELECT_PRODUCTION_POWER_LEADER_CARD", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message ObtainGenericResourceGameMessageTest
     */
    @Test
    void ObtainGenericResourceGameMessageTest() {
        String messageString = "OBTAIN_GENERIC_RESOURCE" + " " + Resource.COIN;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ObtainGenericResourceGameMessage);
        assertEquals("OBTAIN_GENERIC_RESOURCE", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message StartPaymentGameMessageTest
     */
    @Test
    void StartPaymentGameMessageTest() {
        String messageString = "START_PAYMENT";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof StartPaymentGameMessage);
        assertEquals("START_PAYMENT", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message DrawSoloActionTokenGameMessageTest
     */
    @Test
    void DrawSoloActionTokenGameMessageTest() {
        String messageString = "DRAW_SOLO_ACTION_TOKEN";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof DrawSoloActionTokenGameMessage);
        assertEquals("DRAW_SOLO_ACTION_TOKEN", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message SelectAWarehouseDepotsSlotGameMessageTest
     */
    @Test
    void SelectAWarehouseDepotsSlotGameMessageTest() {
        String messageString = "SELECT_A_WAREHOUSE_DEPOTS_SLOT" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof SelectAWarehouseDepotsSlotGameMessage);
        assertEquals("SELECT_A_WAREHOUSE_DEPOTS_SLOT", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message MoveResourcesInWarehouseDepotsGameMessageTest
     */
    @Test
    void MoveResourcesInWarehouseDepotsGameMessageTest() {
        String messageString = "MOVE_RESOURCES_IN_WAREHOUSE_DEPOTS" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof MoveResourcesInWarehouseDepotsGameMessage);
        assertEquals("MOVE_RESOURCES_IN_WAREHOUSE_DEPOTS", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message MoveResourcesWarehouseToESLCGameMessageTest
     */
    @Test
    void MoveResourcesWarehouseToESLCGameMessageTest() {
        String messageString = "MOVE_RESOURCES_WAREHOUSE_TO_ES_LC" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof MoveResourcesWarehouseToESLCGameMessage);
        assertEquals("MOVE_RESOURCES_WAREHOUSE_TO_ES_LC", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message MoveResourceESLCToWarehouseGameMessageTest
     */
    @Test
    void MoveResourceESLCToWarehouseGameMessageTest() {
        String messageString = "MOVE_RESOURCE_ES_LC_TO_WAREHOUSE" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof MoveResourceESLCToWEarehouseGameMessage);
        assertEquals("MOVE_RESOURCE_ES_LC_TO_WAREHOUSE", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message SelectTwoCardsToKeepGameMessageTest
     */
    @Test
    void SelectTwoCardsToKeepGameMessageTest() {
        String messageString = "SELECT_TWO_CARDS_TO_KEEP" + " " + 1 + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof SelectTwoCardsToKeepGameMessage);
        assertEquals("SELECT_TWO_CARDS_TO_KEEP", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message SelectTwoCardsToKeepGameMessageTest
     */
    @Test
    void CurrentPlayerMessageTest() {
        String messageString = "SET_CURRENT_PLAYER" + " " + "nickname";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof CurrentPlayerMessage);
        assertEquals("SET_CURRENT_PLAYER", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }

    /**
     * This method tests creation of message SelectTwoCardsToKeepGameMessageTest
     */
    //@Test
    //void AddResourceToGameMessageTest() {
    //    String messageString = "DISCARD" + " " + LeaderWarehouse.LEADERCARD + " " + 1;
    //    Message message = Message.fromString(messageString);
    //   assertTrue(message instanceof AddResourceToGameMessage);
    //    assertEquals("DISCARD", message.getIdentifier());
    //    assertEquals(message.toString(), messageString);
    //

    /**
     * This method tests creation of message SelectTwoCardsToKeepGameMessageTest
     */
    @Test
    void GameStartServiceMessageTest() {
        String messageString = "GAME_START" + " " + "nickname";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof GameStartServiceMessage);
        assertEquals("GAME_START", message.getIdentifier());
        assertEquals(message.toString(), messageString);
    }
}
