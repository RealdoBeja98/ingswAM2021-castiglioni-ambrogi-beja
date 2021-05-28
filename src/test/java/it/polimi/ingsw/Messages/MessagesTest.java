package it.polimi.ingsw.Messages;

import it.polimi.ingsw.Enums.NormalAction;
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
import it.polimi.ingsw.Messages.ForwardMessages.ShowCardMarketMessage;
import it.polimi.ingsw.Messages.ForwardMessages.ShowCurrentBoardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.ShowMarketMessage;
import it.polimi.ingsw.Messages.ForwardMessages.TransitionForwardMessage;
import it.polimi.ingsw.Messages.GameMessages.SelectNormalActionGameMessage;
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

// <--FIXME--> aggiungere gli altri test degli altri messaggi; i ForwardMessage li testi come i gameMessage

}
