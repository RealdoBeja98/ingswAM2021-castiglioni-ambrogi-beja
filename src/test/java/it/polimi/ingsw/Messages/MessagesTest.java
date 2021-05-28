package it.polimi.ingsw.Messages;

import it.polimi.ingsw.Enums.NormalAction;
import it.polimi.ingsw.Messages.ErrorMessages.NoCardDiscardErrorMessage;
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
