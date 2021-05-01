package it.polimi.ingsw.Table.Decks;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Exceptions.DrawnFromEmptyDeckException;
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
     * This method tests drawing from a deck
     */
    @Test
    void draw() {
        DevelopmentDeck deck = new DevelopmentDeck(999);
        DevelopmentCard card = deck.visualize()[0][0];
        DevelopmentCard cardP = null;
        try {
            cardP = deck.draw(1, 1);
        } catch (DrawnFromEmptyDeckException e) {
            e.printStackTrace();
        }
        assertSame(card, cardP);
        assertThrows(IndexOutOfBoundsException.class, () -> deck.draw(5, 1));
        try {
            cardP = deck.draw(1, 1);
        } catch (DrawnFromEmptyDeckException e) {
            e.printStackTrace();
        }
        try {
            cardP = deck.draw(1, 1);
        } catch (DrawnFromEmptyDeckException e) {
            e.printStackTrace();
        }
        try {
            cardP = deck.draw(1, 1);
        } catch (DrawnFromEmptyDeckException e) {
            e.printStackTrace();
        }
        assertThrows(DrawnFromEmptyDeckException.class, () -> deck.draw(1, 1));
    }

    /**
     * This method tests discarding all the green cards
     */
    @Test
    void discard() {
        DevelopmentDeck deck = new DevelopmentDeck(999);
        DevelopmentCard card = deck.visualize()[2][0];
        Type type = Type.GREEN;
        deck.discard(type);
        DevelopmentCard cardP = deck.visualize()[2][0];
        assertNotSame(card, cardP);
        for(int i = 0; i < 5; i++){
            deck.discard(type);
        }
        assertThrows(RuntimeException.class, () -> deck.discard(type));
    }

}