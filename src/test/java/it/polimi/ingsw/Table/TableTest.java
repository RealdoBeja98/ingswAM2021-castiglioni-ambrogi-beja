package it.polimi.ingsw.Table;
import it.polimi.ingsw.Table.Decks.DevelopmentDeck;
import it.polimi.ingsw.Table.Decks.LeaderDeck;
import it.polimi.ingsw.Table.Market.Market;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the class: Table
 */
public class TableTest {

    /**
     * This method tests the getter of the class and their return
     */
    @Test
    void integrityTest(){
        Table table = new Table(50);
        LeaderDeck leaderDeckTest = table.getLeaderDeck();
        Market marketTest = table.getMarket();
        DevelopmentDeck developmentDeck = table.getDevelopmentDeck();
        assertNotNull(leaderDeckTest);
        assertNotNull(marketTest);
        assertNotNull(developmentDeck);
        leaderDeckTest = null;
        marketTest = null;
        developmentDeck = null;
        assertNotNull(table.getLeaderDeck());
        assertNotNull(table.getMarket());
        assertNotNull(table.getDevelopmentDeck());
        assertNull(leaderDeckTest);
        assertNull(marketTest);
        assertNull(developmentDeck);
    }

}
