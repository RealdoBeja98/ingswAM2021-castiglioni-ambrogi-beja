package it.polimi.ingsw.Table.Decks;

import it.polimi.ingsw.Table.Decks.Leader.LeaderCard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Test class for the class: LeaderDeck
 */
public class LeaderDeckTest {

    /**
     * This method tests the drowing of two cards from LeaderDeck
     */
    @Test
    public void drowTest(){
        LeaderDeck leaderDeck = new LeaderDeck();
        ArrayList<LeaderCard> oldDeck = (ArrayList<LeaderCard>)leaderDeck.getDeck().clone();
        LeaderCard[] drown = leaderDeck.draw();
        ArrayList<LeaderCard> newDeck = leaderDeck.getDeck();
        assertSame(drown[0], oldDeck.get(0));
        assertSame(drown[1], oldDeck.get(1));
        for(int i = 0; i < newDeck.size(); i++){
            assertSame(newDeck.get(i), oldDeck.get(i+2));
        }
    }

}
