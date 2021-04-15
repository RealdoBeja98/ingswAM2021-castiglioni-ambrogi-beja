package it.polimi.ingsw.Table.Decks;
import it.polimi.ingsw.Table.Decks.Leader.LeaderCard;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the class: LeaderDeck
 */
public class LeaderDeckTest {

    /**
     * This method tests the getter of the class and their return
     */
    @Test
    void integrityTest(){
        LeaderDeck leaderDeck = new LeaderDeck();
        ArrayList<LeaderCard> deck = leaderDeck.getDeck();
        assertNotNull(leaderDeck.getDeck().get(0));
        assertNotNull(deck.get(0));
        deck.set(0, null);
        assertNotNull(leaderDeck.getDeck().get(0));
        assertNull(deck.get(0));
    }
    /**
     * This method tests the drawing of two cards from LeaderDeck
     */
    @Test
    void drawTest(){
        LeaderDeck leaderDeck = new LeaderDeck();
        ArrayList<LeaderCard> oldDeck = leaderDeck.getDeck();
        LeaderCard[] drawTwo = leaderDeck.draw();
        ArrayList<LeaderCard> newDeck = leaderDeck.getDeck();
        assertSame(drawTwo[0], oldDeck.get(0));
        assertSame(drawTwo[1], oldDeck.get(1));
        for(int i = 0; i < newDeck.size(); i++){
            assertSame(newDeck.get(i), oldDeck.get(i+2));
        }
    }

}
