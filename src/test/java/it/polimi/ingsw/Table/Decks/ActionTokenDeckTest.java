package it.polimi.ingsw.Table.Decks;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the class: ActionTokenDeck
 */
class ActionTokenDeckTest {

    /**
     * This method tests drawing a card from the action token deck
     */
    @Test
    void draw() {
        ActionTokenDeck deck = new ActionTokenDeck();
        ActionToken token = deck.draw();
        assertSame(token, deck.getDeck().get(deck.getDeck().size()-1));
    }


}
