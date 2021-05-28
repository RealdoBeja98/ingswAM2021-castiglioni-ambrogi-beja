package it.polimi.ingsw.Messages;

import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Enums.NormalAction;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Messages.ErrorMessages.AlreadyDiscardedPositionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.AlreadyEmptyErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.AlreadySelectedSomethingErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.DifferentResourceAlreadyPresentErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.DifferentStorageTypeErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.EmptyDeckErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.FaithMarbleErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameDontExistErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameEndedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GenericResourceErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidEnumErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidMovementErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidPositionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidSelectionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.MissingResourceErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NameTakenErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoCardDiscardErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoCardObtainableErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoCardsPlayErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoDevelopmentCardErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoPLCErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoResourceAErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoResourcePErrorMessage;
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
import it.polimi.ingsw.Messages.ErrorMessages.ResourceAlreadyPresentOtherShelfErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.TypoErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.WhiteMarbleErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.WrongResourceErrorMessage;
import it.polimi.ingsw.Messages.ForwardMessages.AddedResourceToForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.AdvanceFaithTrackForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.DiscardedLeaderCardForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.MovedResourceESLCToWarehouseForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.MovedResourcesInWarehouseDepotsForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.ObtainedGenericResourceForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.PayedWithExtraStorageLeaderCardForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.PayedWithStrongboxForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.PayedWithWarehouseDepotsForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.PlacedDevelopmentCardForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.PlayedLeaderCardForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.SelectedTwoCardsToKeepForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.ShowCardMarketMessage;
import it.polimi.ingsw.Messages.ForwardMessages.ShowCurrentBoardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.ShowMarketMessage;
import it.polimi.ingsw.Messages.ForwardMessages.TransitionForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.UpdateDevelopmentCardForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.UpdateMarketForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.UpdateObtainedMultipleResourceForwardMessage;
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
import it.polimi.ingsw.Messages.GameMessages.SelectDefaultProductionPowerGameMessage;
import it.polimi.ingsw.Messages.GameMessages.SelectNormalActionGameMessage;
import it.polimi.ingsw.Messages.GameMessages.SelectProductionDevelopmentCardGameMessage;
import it.polimi.ingsw.Messages.GameMessages.SelectProductionPowerLeaderCardGameMessage;
import it.polimi.ingsw.Messages.GameMessages.SelectTwoCardsToKeepGameMessage;
import it.polimi.ingsw.Messages.GameMessages.StartPaymentGameMessage;
import it.polimi.ingsw.Messages.GameMessages.TakeResourcesFromTheMarketGameMessage;
import it.polimi.ingsw.Messages.ServiceMessages.GameStartServiceMessage;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;
import it.polimi.ingsw.Table.Decks.Token.GreenActionToken;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for creation of messages
 */
public class MessagesTest {

    /**
     * This method tests creation of message NoCardDiscardErrorMessage
     */
    @Test
    void NoCardDiscardErrorMessageTest(){
        String messageString = "ERROR_NO_CARDS_DISCARD";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoCardDiscardErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_NO_CARDS_DISCARD"));
    }

    /**
     * This method tests creation of message NotYourTurnErrorMessage
     */
    @Test
    void NotYourTurnErrorMessageTest(){
        String messageString = "ERROR_NOT_YOUR_TURN";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NotYourTurnErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_NOT_YOUR_TURN"));
    }

    /**
     * This method tests creation of message InvalidActionErrorMessage 
     */
    @Test
    void InvalidActionErrorMessageTest(){
        String messageString = "ERROR_INVALID_ACTION";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof InvalidActionErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_INVALID_ACTION"));
    }


    /**
     * This method tests creation of message AlreadyDiscardedPositionErrorMessageTest
     */
    @Test
    void AlreadyDiscardedPositionErrorMessageTest(){
        String messageString = "ERROR_ALREADY_DISCARDED_POSITION";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof AlreadyDiscardedPositionErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_ALREADY_DISCARDED_POSITION"));
    }
    /**
     * This method tests creation of message GameEndedErrorMessageTest
     */
    @Test
    void GameEndedErrorMessageTest(){
        String messageString = "ERROR_GAME_ENDED";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof GameEndedErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_GAME_ENDED"));
    }
    /**
     * This method tests creation of message InvalidPositionErrorMessageTest
     */
    @Test
    void InvalidPositionErrorMessageTest(){
        String messageString = "ERROR_INVALID_POSITION";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof InvalidPositionErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_INVALID_POSITION"));
    }
    /**
     * This method tests creation of message TypoErrorMessageTest
     */
    @Test
    void TypoErrorMessageTest(){
        String messageString = "ERROR_TYPO";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof TypoErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_TYPO"));
    }
    /**
     * This method tests creation of message NoCardsPlayErrorMessageTest
     */
    @Test
    void NoCardsPlayErrorMessageTest(){
        String messageString = "ERROR_NO_CARDS_PLAY";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoCardsPlayErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_NO_CARDS_PLAY"));
    }
    /**
     * This method tests creation of message RequirementsErrorMessageTest
     */
    @Test
    void RequirementsErrorMessageTest(){
        String messageString = "ERROR_REQUIREMENTS";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof RequirementsErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_REQUIREMENTS"));
    }
    /**
     * This method tests creation of message InvalidEnumErrorMessageTest
     */
    @Test
    void InvalidEnumErrorMessageTest(){
        String messageString = "ERROR_INVALID_ENUM";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof InvalidEnumErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_INVALID_ENUM"));
    }
    /**
     * This method tests creation of message NoResourceAErrorMessageTest
     */
    @Test
    void NoResourceAErrorMessageTest(){
        String messageString = "ERROR_NO_RESOURCE_A";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoResourceAErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_NO_RESOURCE_A"));
    }
    /**
     * This method tests creation of message DifferentStorageTypeErrorMessageTest
     */
    @Test
    void DifferentStorageTypeErrorMessageTest(){
        String messageString = "ERROR_DIFFERENT_STORAGE_TYPE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof DifferentStorageTypeErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_DIFFERENT_STORAGE_TYPE"));
    }
    /**
     * This method tests creation of message OccupiedSlotLCErrorMessageTest
     */
    @Test
    void OccupiedSlotLCErrorMessageTest(){
        String messageString = "ERROR_OCCUPIED_SLOT_LC";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof OccupiedSlotLCErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_OCCUPIED_SLOT_LC"));
    }
    /**
     * This method tests creation of message OccupiedSlotWDErrorMessageTest
     */
    @Test
    void OccupiedSlotWDErrorMessageTest(){
        String messageString = "ERROR_OCCUPIED_SLOT_WD";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof OccupiedSlotWDErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_OCCUPIED_SLOT_WD"));
    }
    /**
     * This method tests creation of message DifferentResourceAlreadyPresentErrorMessageTest
     */
    @Test
    void DifferentResourceAlreadyPresentErrorMessageTest(){
        String messageString = "ERROR_DIFFERENT_RESOURCE_ALREADY_PRESENT";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof DifferentResourceAlreadyPresentErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_DIFFERENT_RESOURCE_ALREADY_PRESENT"));
    }
    /**
     * This method tests creation of message WhiteMarbleErrorMessageTest
     */
    @Test
    void WhiteMarbleErrorMessageTest(){
        String messageString = "ERROR_WHITE_MARBLE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof WhiteMarbleErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_WHITE_MARBLE"));
    }
    /**
     * This method tests creation of message FaithMarbleErrorMessageTest
     */
    @Test
    void FaithMarbleErrorMessageTest(){
        String messageString = "ERROR_FAITH_MARBLE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof FaithMarbleErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_FAITH_MARBLE"));
    }
    /**
     * This method tests creation of message NoWhiteMarbleErrorMessageTest
     */
    @Test
    void NoWhiteMarbleErrorMessageTest(){
        String messageString = "ERROR_NO_WHITE_MARBLE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoWhiteMarbleErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_NO_WHITE_MARBLE"));
    }
    /**
     * This method tests creation of message AlreadySelectedSomethingErrorMessageTest
     */
    @Test
    void AlreadySelectedSomethingErrorMessageTest(){
        String messageString = "ERROR_ALREADY_SELECTED_SOMETHING";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof AlreadySelectedSomethingErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_ALREADY_SELECTED_SOMETHING"));
    }
    /**
     * This method tests creation of message NotEnoughResourcesErrorMessageTest
     */
    @Test
    void NotEnoughResourcesErrorMessageTest(){
        String messageString = "ERROR_NOT_ENOUGH_RESOURCES";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NotEnoughResourcesErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_NOT_ENOUGH_RESOURCES"));
    }
    /**
     * This method tests creation of message EmptyDeckErrorMessageTest
     */
    @Test
    void EmptyDeckErrorMessageTest(){
        String messageString = "ERROR_EMPTY_DECK";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof EmptyDeckErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_EMPTY_DECK"));
    }
    /**
     * This method tests creation of message InvalidSelectionErrorMessageTest
     */
    @Test
    void InvalidSelectionErrorMessageTest(){
        String messageString = "ERROR_INVALID_SELECTION";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof InvalidSelectionErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_INVALID_SELECTION"));
    }
    /**
     * This method tests creation of message WrongResourceErrorMessageTest
     */
    @Test
    void WrongResourceErrorMessageTest(){
        String messageString = "ERROR_WRONG_RESOURCE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof WrongResourceErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_WRONG_RESOURCE"));
    }
    /**
     * This method tests creation of message MissingResourceErrorMessageTest
     */
    @Test
    void MissingResourceErrorMessageTest(){
        String messageString = "ERROR_MISSING_RESOURCE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof MissingResourceErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_MISSING_RESOURCE"));
    }
    /**
     * This method tests creation of message NotStrongboxErrorMessageTest
     */
    @Test
    void NotStrongboxErrorMessageTest(){
        String messageString = "ERROR_NOT_STRONGBOX";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NotStrongboxErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_NOT_STRONGBOX"));
    }
    /**
     * This method tests creation of message AlreadyEmptyErrorMessageTest
     */
    @Test
    void AlreadyEmptyErrorMessageTest(){
        String messageString = "ERROR_ALREADY_EMPTY";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof AlreadyEmptyErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_ALREADY_EMPTY"));
    }
    /**
     * This method tests creation of message NotEsErrorMessageTest
     */
    @Test
    void NotEsErrorMessageTest(){
        String messageString = "ERROR_NOT_ES";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NotEsErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_NOT_ES"));
    }
    /**
     * This method tests creation of message NoCardObtainableErrorMessageTest
     */
    @Test
    void NoCardObtainableErrorMessageTest(){
        String messageString = "ERROR_NO_CARD_OBTAINABLE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoCardObtainableErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_NO_CARD_OBTAINABLE"));
    }
    /**
     * This method tests creation of message NoDevelopmentCardErrorMessageTest
     */
    @Test
    void NoDevelopmentCardErrorMessageTest(){
        String messageString = "ERROR_NO_DEVELOPMENT_CARD";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoDevelopmentCardErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_NO_DEVELOPMENT_CARD"));
    }
    /**
     * This method tests creation of message NoPLCErrorMessageTest
     */
    @Test
    void NoPLCErrorMessageTest(){
        String messageString = "ERROR_NO_PLC";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoPLCErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_NO_PLC"));
    }
    /**
     * This method tests creation of message GenericResourceErrorMessageTest
     */
    @Test
    void GenericResourceErrorMessageTest(){
        String messageString = "ERROR_GENERIC_RESOURCE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof GenericResourceErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_GENERIC_RESOURCE"));
    }
    /**
     * This method tests creation of message NotEnoughRErrorMessageTest
     */
    @Test
    void NotEnoughRErrorMessageTest(){
        String messageString = "ERROR_NOT_ENOUGH_R";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NotEnoughRErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_NOT_ENOUGH_R"));
    }
    /**
     * This method tests creation of message NoSelectedPowersErrorMessageTest
     */
    @Test
    void NoSelectedPowersErrorMessageTest(){
        String messageString = "ERROR_NO_SELECTED_POWERS";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof NoSelectedPowersErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_NO_SELECTED_POWERS"));
    }
    /**
     * This method tests creation of message InvalidMovementErrorMessageTest
     */
    @Test
    void InvalidMovementErrorMessageTest(){
        String messageString = "ERROR_INVALID_MOVEMENT";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof InvalidMovementErrorMessage);
        assertTrue(message.getIdentifier().equals("ERROR_INVALID_MOVEMENT"));
    }
    /**
     * This method tests creation of message SelectNormalActionGameMessage
     */
    @Test
    void SelectNormalActionGameMessageTest(){
        String messageString = "SELECT_NORMAL_ACTION" + " " + NormalAction.ACTIVATE_PRODUCTION;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof SelectNormalActionGameMessage);
        assertTrue(message.getIdentifier().equals("SELECT_NORMAL_ACTION"));
        assertTrue(message.toString().equals(messageString));
    }
    /**
     * This method tests creation of message TransitionForwardMessageTest
     */
    @Test
    void TransitionForwardMessageTest(){
        String messageString = "TRANSITION_MESSAGE";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof TransitionForwardMessage);
        assertTrue(message.getIdentifier().equals("TRANSITION_MESSAGE"));
        assertTrue(message.toString().equals(messageString));
    }
    /**
     * This method tests creation of message ConfirmedActionMessageTest
     */
    @Test
    void ConfirmedActionMessageTest(){
        String messageString = "ConfirmedActionMessage" ;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ConfirmedActionMessage);
    }
    /**
     * This method tests creation of message ShowCurrentBoardMessageTest
     */
    @Test
    void ShowCurrentBoardMessageTest(){
        String messageString = "SHOW_CURRENT_BOARD";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ShowCurrentBoardMessage);
        assertTrue(message.getIdentifier().equals("SHOW_CURRENT_BOARD"));
        assertTrue(message.toString().equals(messageString));
    }
   /**
     * This method tests creation of message ShowCardMarketMessageTest
     */
    @Test
    void ShowCardMarketMessageTest(){
        String messageString = "SHOW_CARD_MARKET";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ShowCardMarketMessage);
        assertTrue(message.getIdentifier().equals("SHOW_CARD_MARKET"));
        assertTrue(message.toString().equals(messageString));
    } 
    /**
     * This method tests creation of message UpdateMarketForwardMessageTest
     */
    @Test
    void UpdateMarketForwardMessageTest(){
        String messageString = "UPDATE_MARKET" + " " + "nickname" + " " + RowColumn.COLUMN + " " + 2 ;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof UpdateMarketForwardMessage);
        assertTrue(message.getIdentifier().equals("UPDATE_MARKET"));
        assertTrue(message.toString().equals(messageString));

    }
    
    /**
     * This method tests creation of message UpdateDevelopmentCardForwardMessageTest
     */
    @Test
    void UpdateDevelopmentCardForwardMessageTest(){
        String messageString = "UPDATE_DEVELOPMENT_CARD" + " " + 1 + " " + 2;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof UpdateDevelopmentCardForwardMessage);
        assertTrue(message.getIdentifier().equals("UPDATE_DEVELOPMENT_CARD"));
        assertTrue(message.toString().equals(messageString));
    } 
    /**
     * This method tests creation of message UpdateSoloActionTokenMessage
     */
    @Test
    void UpdateSoloActionTokenMessage(){
        String messageString = "UPDATE_SOLO_ACTION_TOKEN" + " " + new GreenActionToken();
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof UpdateSoloActionTokenMessage);
        assertTrue(message.getIdentifier().equals("UPDATE_SOLO_ACTION_TOKEN"));
        assertTrue(message.toString().equals(messageString));
    } 
    
    /**
     * This method tests creation of message AdvanceFaithTrackForwardMessageTest
     */
    @Test
    void AdvanceFaithTrackForwardMessageTest(){
        String messageString = "ADVANCE_FAITH_TRACK" + " " + "nickname";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof AdvanceFaithTrackForwardMessage);
        assertTrue(message.getIdentifier().equals("ADVANCE_FAITH_TRACK"));
        assertTrue(message.toString().equals(messageString));
    } 
    /**
     * This method tests creation of message DiscardedLeaderCardForwardMessageTest
     */
    @Test
    void DiscardedLeaderCardForwardMessageTest(){
        String messageString = "DISCARDED_LEADER_CARD"+ " " + "nickname" + " " + 1 ;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof DiscardedLeaderCardForwardMessage);
        assertTrue(message.getIdentifier().equals("DISCARDED_LEADER_CARD"));
        assertTrue(message.toString().equals(messageString));
    } 
    /**
     * This method tests creation of message PlayedLeaderCardForwardMessageTest
     */
    @Test
    void PlayedLeaderCardForwardMessageTest(){
        String messageString = "PLAYED_LEADER_CARD" + " " + "nickname" + " " + 1 ;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PlayedLeaderCardForwardMessage);
        assertTrue(message.getIdentifier().equals("PLAYED_LEADER_CARD"));
        assertTrue(message.toString().equals(messageString));
    } 
    /**
     * This method tests creation of message AddedResourceToForwardMessageTest
     */
    @Test
    void AddedResourceToForwardMessageTest(){
        String messageString = "ADDED_RESOURCE_TO"+ " " + "nickname" + " " + LeaderWarehouse.DISCARD + " " + Resource.COIN + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof AddedResourceToForwardMessage);
        assertTrue(message.getIdentifier().equals("ADDED_RESOURCE_TO"));
        assertTrue(message.toString().equals(messageString));
    } 
    /**
     * This method tests creation of message PlacedDevelopmentCardForwardMessageTest
     */
    @Test
    void PlacedDevelopmentCardForwardMessageTest(){
        Resource[] cost = {Resource.COIN, Resource.SERVANT, Resource.SHIELD};
        int[] costNumber = {1, 2, 3};
        Resource[] requirements = {Resource.COIN};
        int[] costRequirements = {3};
        Resource[] products = {Resource.FAITH, Resource.SERVANT};
        int[] costProducts = {2,1};
        DevelopmentCard developmentCard = new DevelopmentCard(cost, costNumber, Type.GREEN, 2,
                requirements, costRequirements, products, costProducts, 5);
        String messageString = "PLACED_DEVELOPMENT_CARD" + " " + "nickname" + " " + 1 + " " + developmentCard.export();
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PlacedDevelopmentCardForwardMessage);
        assertTrue(message.getIdentifier().equals("PLACED_DEVELOPMENT_CARD"));
        assertTrue(message.toString().equals(messageString));
    } 
    /**
     * This method tests creation of message UpdateObtainedMultipleResourceForwardMessageTest
     */
    @Test
    void UpdateObtainedMultipleResourceForwardMessageTest(){
        String messageString = "UPDATE_MULTIPLE_RESOURCES" + " " + "nickname" + " " + 1 + " " + 1 + " " + 1 + " " + 1 + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof UpdateObtainedMultipleResourceForwardMessage);
        assertTrue(message.getIdentifier().equals("UPDATE_MULTIPLE_RESOURCES"));
        assertTrue(message.toString().equals(messageString));
    } 
    /**
     * This method tests creation of message ObtainedGenericResourceForwardMessageTest
     */
    @Test
    void ObtainedGenericResourceForwardMessageTest(){
        String messageString = "OBTAINED_GENERIC_RESOURCE" + " " + "nickname" + " " + Resource.COIN;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ObtainedGenericResourceForwardMessage);
        assertTrue(message.getIdentifier().equals("OBTAINED_GENERIC_RESOURCE"));
        assertTrue(message.toString().equals(messageString));
    } 
    /**
     * This method tests creation of message PayedWithExtraStorageLeaderCardForwardMessageTest
     */
    @Test
    void PayedWithExtraStorageLeaderCardForwardMessageTest(){
        String messageString = "PAYED_WITH_EXTRA_STORAGE_LEADER_CARD" + " " + "nickname" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PayedWithExtraStorageLeaderCardForwardMessage);
        assertTrue(message.getIdentifier().equals("PAYED_WITH_EXTRA_STORAGE_LEADER_CARD"));
        assertTrue(message.toString().equals(messageString));
    } 
    /**
     * This method tests creation of message SelectNormalActionGameMessage
     */
    //@Test<--FIXME TEST
    //void PayedWithStrongboxForwardMessageTest(){
    //    String messageString = "PAYED_WITH_STRONGBOXPAYED_WITH_STRONGBOX" + " " + "nickname" + " " + Resource.SERVANT;
    //    Message message = Message.fromString(messageString);
    //    assertTrue(message instanceof PayedWithStrongboxForwardMessage);
    //    //assertTrue(message.getIdentifier().equals("PAYED_WITH_STRONGBOX"));
    //    //assertTrue(message.toString().equals(messageString));
    //}

    /*
     * This method tests creation of message PayedWithWarehouseDepotsForwardMessageTest
     */
    @Test
    void PayedWithWarehouseDepotsForwardMessageTest(){
        String messageString = "PAYED_WITH_WAREHOUSE_DEPOTS" + " " + "nickname" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PayedWithWarehouseDepotsForwardMessage);
        assertTrue(message.getIdentifier().equals("PAYED_WITH_WAREHOUSE_DEPOTS"));
        assertTrue(message.toString().equals(messageString));
    }

    /* 
     * This method tests creation of message MovedResourcesInWarehouseDepotsForwardMessageTest
     */
    @Test
    void MovedResourcesInWarehouseDepotsForwardMessageTest(){
        String messageString = "MOVED_RESOURCES_IN_WAREHOUSE_DEPOTS" + " " + "nickname" + " " + 1 + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof MovedResourcesInWarehouseDepotsForwardMessage);
        assertTrue(message.getIdentifier().equals("MOVED_RESOURCES_IN_WAREHOUSE_DEPOTS"));
        assertTrue(message.toString().equals(messageString));
    }
    
    /*
     * This method tests creation of message MovedResourceESLCToWarehouseForwardMessageTest
     */
    
    @Test
    void MovedResourceESLCToWarehouseForwardMessageTest(){
        String messageString = "MOVED_RESOURCE_ES_LC_TO_WAREHOUSE" + " " + "nickname" + " " + 1 + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof MovedResourceESLCToWarehouseForwardMessage);
        assertTrue(message.getIdentifier().equals("MOVED_RESOURCE_ES_LC_TO_WAREHOUSE"));
        assertTrue(message.toString().equals(messageString));
    }
    
    /**
     * This method tests creation of message SelectedTwoCardsToKeepForwardMessageTest
     */
    @Test
    void SelectedTwoCardsToKeepForwardMessageTest(){
        String messageString = "SELECTED_TWO_CARDS_TO_KEEP" + " " + "nickname" + " " + 1 + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof SelectedTwoCardsToKeepForwardMessage);
        assertTrue(message.getIdentifier().equals("SELECTED_TWO_CARDS_TO_KEEP"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message ChooseDiscardLeaderCardGameMessageTest
     */
    @Test
    void ChooseDiscardLeaderCardGameMessageTest(){
        String messageString = "CHOOSE_DISCARD_LEADER_CARD";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ChooseDiscardLeaderCardGameMessage);
        assertTrue(message.getIdentifier().equals("CHOOSE_DISCARD_LEADER_CARD"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message DiscardLeaderCardGameMessageTest
     */
    @Test
    void DiscardLeaderCardGameMessageTest(){
        String messageString = "DISCARD_LEADER_CARD" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof DiscardLeaderCardGameMessage);
        assertTrue(message.getIdentifier().equals("DISCARD_LEADER_CARD"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message ChoosePlayLeaderCardGameMessageTest
     */
    @Test
    void ChoosePlayLeaderCardGameMessageTest(){
        String messageString = "CHOOSE_PLAY_LEADER_CARD";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ChoosePlayLeaderCardGameMessage);
        assertTrue(message.getIdentifier().equals("CHOOSE_PLAY_LEADER_CARD"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message PlayLeaderCardGameMessageTest
     */
    @Test
    void PlayLeaderCardGameMessageTest(){
        String messageString = "PLAY_LEADER_CARD" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PlayLeaderCardGameMessage);
        assertTrue(message.getIdentifier().equals("PLAY_LEADER_CARD"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message ChooseNoActionLeaderCardGameMessageTest
     */
    @Test
    void ChooseNoActionLeaderCardGameMessageTest(){
        String messageString = "CHOOSE_NO_ACTION_LEADER_CARD";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ChooseNoActionLeaderCardGameMessage);
        assertTrue(message.getIdentifier().equals("CHOOSE_NO_ACTION_LEADER_CARD"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message EndTurnGameMessageTest
     */
    @Test
    void EndTurnGameMessageTest(){
        String messageString = "END_TURN";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof EndTurnGameMessage);
        assertTrue(message.getIdentifier().equals("END_TURN"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message TakeResourcesFromTheMarketGameMessageTest
     */
    @Test
    void TakeResourcesFromTheMarketGameMessageTest(){
        String messageString = "TAKE_RESOURCES_FROM_THE_MARKET" + " " + RowColumn.COLUMN + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof TakeResourcesFromTheMarketGameMessage);
        assertTrue(message.getIdentifier().equals("TAKE_RESOURCES_FROM_THE_MARKET"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message ChangeWhiteMarbleWithGameMessageTest
     */
    @Test
    void ChangeWhiteMarbleWithGameMessageTest(){
        String messageString = "CHANGE_WHITE_MARBLE_WITH" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ChangeWhiteMarbleWithGameMessage);
        assertTrue(message.getIdentifier().equals("CHANGE_WHITE_MARBLE_WITH"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message BuyDevelopmentCardGameMessageTest
     */
    @Test
    void BuyDevelopmentCardGameMessageTest(){
        String messageString = "BUY_DEVELOPMENT_CARD" + " " + 1 + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof BuyDevelopmentCardGameMessage);
        assertTrue(message.getIdentifier().equals("BUY_DEVELOPMENT_CARD"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message PayWithStrongboxGameMessageTest
     */
    @Test
    void PayWithStrongboxGameMessageTest(){
        String messageString = "PAY_WITH_STRONGBOX" + " " + Resource.COIN;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PayWithStrongboxGameMessage);
        assertTrue(message.getIdentifier().equals("PAY_WITH_STRONGBOX"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message PayWithWarehouseDepotsGameMessageTest
     */
    @Test
    void PayWithWarehouseDepotsGameMessageTest(){
        String messageString = "PAY_WITH_WAREHOUSE_DEPOTS" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PayWithWarehouseDepotsGameMessage);
        assertTrue(message.getIdentifier().equals("PAY_WITH_WAREHOUSE_DEPOTS"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message PayWithExtraStorageLeaderCardGameMessageTest
     */
    @Test
    void PayWithExtraStorageLeaderCardGameMessageTest(){
        String messageString = "PAY_WITH_EXTRA_STORAGE_LEADER_CARD" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PayWithExtraStorageLeaderCardGameMessage);
        assertTrue(message.getIdentifier().equals("PAY_WITH_EXTRA_STORAGE_LEADER_CARD"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message PlaceDevelopmentCardGameMessageTest
     */
    @Test
    void PlaceDevelopmentCardGameMessageTest(){
        String messageString = "PLACE_DEVELOPMENT_CARD" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof PlaceDevelopmentCardGameMessage);
        assertTrue(message.getIdentifier().equals("PLACE_DEVELOPMENT_CARD"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message SelectProductionDevelopmentCardGameMessageTest
     */
    @Test
    void SelectProductionDevelopmentCardGameMessageTest(){
        String messageString = "SELECT_PRODUCTION_DEVELOPMENT_CARD" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof SelectProductionDevelopmentCardGameMessage);
        assertTrue(message.getIdentifier().equals("SELECT_PRODUCTION_DEVELOPMENT_CARD"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message SelectProductionPowerLeaderCardGameMessageTest
     */
    @Test
    void SelectProductionPowerLeaderCardGameMessageTest(){
        String messageString = "SELECT_PRODUCTION_POWER_LEADER_CARD" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof SelectProductionPowerLeaderCardGameMessage);
        assertTrue(message.getIdentifier().equals("SELECT_PRODUCTION_POWER_LEADER_CARD"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message ObtainGenericResourceGameMessageTest
     */
    @Test
    void ObtainGenericResourceGameMessageTest(){
        String messageString = "OBTAIN_GENERIC_RESOURCE" + " " + Resource.COIN;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof ObtainGenericResourceGameMessage);
        assertTrue(message.getIdentifier().equals("OBTAIN_GENERIC_RESOURCE"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message StartPaymentGameMessageTest
     */
    @Test
    void StartPaymentGameMessageTest(){
        String messageString = "START_PAYMENT";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof StartPaymentGameMessage);
        assertTrue(message.getIdentifier().equals("START_PAYMENT"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message DrawSoloActionTokenGameMessageTest
     */
    @Test
    void DrawSoloActionTokenGameMessageTest(){
        String messageString = "DRAW_SOLO_ACTION_TOKEN";
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof DrawSoloActionTokenGameMessage);
        assertTrue(message.getIdentifier().equals("DRAW_SOLO_ACTION_TOKEN"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message SelectAWarehouseDepotsSlotGameMessageTest
     */
    @Test
    void SelectAWarehouseDepotsSlotGameMessageTest(){
        String messageString = "SELECT_A_WAREHOUSE_DEPOTS_SLOT" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof SelectAWarehouseDepotsSlotGameMessage);
        assertTrue(message.getIdentifier().equals("SELECT_A_WAREHOUSE_DEPOTS_SLOT"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message MoveResourcesInWarehouseDepotsGameMessageTest
     */
    @Test
    void MoveResourcesInWarehouseDepotsGameMessageTest(){
        String messageString = "MOVE_RESOURCES_IN_WAREHOUSE_DEPOTS" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof MoveResourcesInWarehouseDepotsGameMessage);
        assertTrue(message.getIdentifier().equals("MOVE_RESOURCES_IN_WAREHOUSE_DEPOTS"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message MoveResourcesWarehouseToESLCGameMessageTest
     */
    @Test
    void MoveResourcesWarehouseToESLCGameMessageTest(){
        String messageString = "MOVE_RESOURCES_WAREHOUSE_TO_ES_LC" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof MoveResourcesWarehouseToESLCGameMessage);
        assertTrue(message.getIdentifier().equals("MOVE_RESOURCES_WAREHOUSE_TO_ES_LC"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message MoveResourceESLCToWEarehouseGameMessageTest
     */
    @Test
    void MoveResourceESLCToWEarehouseGameMessageTest(){
        String messageString = "MOVE_RESOURCE_ES_LC_TO_WAREHOUSE" + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof MoveResourceESLCToWEarehouseGameMessage);
        assertTrue(message.getIdentifier().equals("MOVE_RESOURCE_ES_LC_TO_WAREHOUSE"));
        assertTrue(message.toString().equals(messageString));
    }

    /**
     * This method tests creation of message SelectTwoCardsToKeepGameMessageTest
     */
    @Test
    void SelectTwoCardsToKeepGameMessageTest(){
        String messageString = "SELECT_TWO_CARDS_TO_KEEP" + " " + 1 + " " + 1;
        Message message = Message.fromString(messageString);
        assertTrue(message instanceof SelectTwoCardsToKeepGameMessage);
        assertTrue(message.getIdentifier().equals("SELECT_TWO_CARDS_TO_KEEP"));
        assertTrue(message.toString().equals(messageString));
    }
}
