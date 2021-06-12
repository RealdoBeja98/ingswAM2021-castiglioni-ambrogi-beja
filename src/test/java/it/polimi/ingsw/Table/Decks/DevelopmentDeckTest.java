package it.polimi.ingsw.Table.Decks;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Exceptions.DrawnFromEmptyDeckException;
import it.polimi.ingsw.Exceptions.IndexOutOfDevelopmentDeckException;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the class: DevelopmentDeck
 */
class DevelopmentDeckTest {

    /**
     * This method tests the visualization of the deck tray
     */
    @Test
    void visualize() {
        DevelopmentDeck deck = new DevelopmentDeck(999);
        DevelopmentCard card = deck.visualize()[2][0];
        assertSame(card.getLevel(), 1);
        assertSame(card.getType(), Type.GREEN);

        DevelopmentCard card2 = deck.visualize()[1][3];
        assertSame(card2.getLevel(), 2);
        assertSame(card2.getType(), Type.PURPLE);

    }

    /**
     * This method tests if all card of a type are finished
     */
    @Test
    void allCardOfATypeFinished() {
        DevelopmentDeck deck = new DevelopmentDeck(999);
        try {
            deck.draw(1,1);
            deck.draw(1,1);
            deck.draw(1,1);
            deck.draw(1,1);
            deck.draw(2,1);
            deck.draw(2,1);
            deck.draw(2,1);
            deck.draw(2,1);
            deck.draw(3,1);
            deck.draw(3,1);
            deck.draw(3,1);
            assertFalse(deck.allCardOfATypeFinished());
            deck.draw(3,1);
            assertTrue(deck.allCardOfATypeFinished());
        } catch (DrawnFromEmptyDeckException | IndexOutOfDevelopmentDeckException e) {
            fail();
        }
    }

    /**
     * This method tests drawing from a deck
     */
    @Test
    void draw() {
        DevelopmentDeck deck = new DevelopmentDeck(999);
        DevelopmentCard card = deck.visualize()[0][0];
        DevelopmentCard cardP = null;
        try {
            cardP = deck.draw(1, 1);
        } catch (DrawnFromEmptyDeckException | IndexOutOfDevelopmentDeckException e) {
            fail();
        }
        assertSame(card, cardP);
        assertThrows(IndexOutOfDevelopmentDeckException.class, () -> deck.draw(5, 1));
        try {
            cardP = deck.draw(1, 1);
        } catch (DrawnFromEmptyDeckException | IndexOutOfDevelopmentDeckException e) {
            fail();
        }
        try {
            cardP = deck.draw(1, 1);
        } catch (DrawnFromEmptyDeckException | IndexOutOfDevelopmentDeckException e) {
            fail();
        }
        try {
            cardP = deck.draw(1, 1);
        } catch (DrawnFromEmptyDeckException | IndexOutOfDevelopmentDeckException e) {
            fail();
        }
        assertThrows(DrawnFromEmptyDeckException.class, () -> deck.draw(1, 1));
    }

    /**
     * This method tests discarding all the green cards
     */
    @Test
    void discard() {
        DevelopmentDeck deck = new DevelopmentDeck(999);
        DevelopmentCard card1 = deck.visualize()[2][0];
        Type type = Type.GREEN;
        deck.discard(type);
        DevelopmentCard card2 = deck.visualize()[2][0];
        assertNotNull(card1);
        assertNotNull(card2);
        assertNotSame(card1, card2);
        for(int i = 0; i < 5; i++){
            deck.discard(type);
        }
        DevelopmentCard card3 = deck.visualize()[2][0];
        assertNull(card3);
    }

    /**
     * This method tests creating a DevelopmentDeck from an exported string
     */
    @Test
    void testExportDevelopmentDeck(){
        DevelopmentDeck deck = new DevelopmentDeck(999);
        assertEquals(deck.export(), (new DevelopmentDeck(deck.export())).export());
    }

}