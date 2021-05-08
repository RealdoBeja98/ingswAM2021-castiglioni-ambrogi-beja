package it.polimi.ingsw.Table.Market;
import static org.junit.jupiter.api.Assertions.*;
import it.polimi.ingsw.Table.Market.Marbles.Marble;
import org.junit.jupiter.api.Test;

/**
 * Test class for the class: Market
 */
public class MarketTest {

    /**
     * This method tests the market behaviour when we choose a column
     */
    @Test
    void testMarketChooseColumn(){
        Market market = new Market();
        Marble[][] oldMarketTray = market.getMarketTray();
        Marble oldExtraMarble = market.getExtraMarble();
        Marble[] chosenMarbles = market.chooseColumn(1);
        assertSame(chosenMarbles[0], oldMarketTray[0][1]);
        assertSame(chosenMarbles[1], oldMarketTray[1][1]);
        assertSame(chosenMarbles[2], oldMarketTray[2][1]);
        assertSame(market.getExtraMarble(), oldMarketTray[0][1]);
        assertSame(oldExtraMarble, market.getMarketTray()[2][1]);
        assertSame(chosenMarbles[0], market.getExtraMarble());
        assertSame(chosenMarbles[1], market.getMarketTray()[0][1]);
        assertSame(chosenMarbles[2], market.getMarketTray()[1][1]);
    }

    /**
     * This method tests the market behaviour when we choose a row
     */
    @Test
    void testMarketChooseRow(){
        Market market = new Market();
        Marble[][] oldMarketTray = market.getMarketTray();
        Marble oldExtraMarble = market.getExtraMarble();
        Marble[] chosenMarbles = market.chooseRow(2);
        assertSame(chosenMarbles[0], oldMarketTray[2][0]);
        assertSame(chosenMarbles[1], oldMarketTray[2][1]);
        assertSame(chosenMarbles[2], oldMarketTray[2][2]);
        assertSame(chosenMarbles[3], oldMarketTray[2][3]);
        assertSame(market.getExtraMarble(), oldMarketTray[2][0]);
        assertSame(oldExtraMarble, market.getMarketTray()[2][3]);
        assertSame(chosenMarbles[0], market.getExtraMarble());
        assertSame(chosenMarbles[1], market.getMarketTray()[2][0]);
        assertSame(chosenMarbles[2], market.getMarketTray()[2][1]);
        assertSame(chosenMarbles[3], market.getMarketTray()[2][2]);
    }

    /**
     * This method tests the market behaviour when we choose a row and a column
     */
    @Test
    void testMarketChooseRowAndColumn(){
        Market market = new Market();
        Marble[][] oldMarketTray = market.getMarketTray();
        Marble oldExtraMarble = market.getExtraMarble();
        market.chooseRow(1);
        market.chooseColumn(2);
        Marble[][] newMarketTray = market.getMarketTray();
        Marble newExtraMarble = market.getExtraMarble();
        assertSame(newMarketTray[0][0], oldMarketTray[0][0]);
        assertSame(newMarketTray[0][1], oldMarketTray[0][1]);
        assertSame(newMarketTray[0][2], oldMarketTray[1][3]);
        assertSame(newMarketTray[0][3], oldMarketTray[0][3]);
        assertSame(newMarketTray[1][0], oldMarketTray[1][1]);
        assertSame(newMarketTray[1][1], oldMarketTray[1][2]);
        assertSame(newMarketTray[1][2], oldMarketTray[2][2]);
        assertSame(newMarketTray[1][3], oldExtraMarble);
        assertSame(newMarketTray[2][0], oldMarketTray[2][0]);
        assertSame(newMarketTray[2][1], oldMarketTray[2][1]);
        assertSame(newMarketTray[2][2], oldMarketTray[1][0]);
        assertSame(newMarketTray[2][3], oldMarketTray[2][3]);
        assertSame(newExtraMarble, oldMarketTray[0][2]);
    }

    /**
     * This method tests creating a Market from an exported string
     */
    @Test
    void testExportMarket(){
        Market market = new Market();
        Marble[][] oldMarketTray = market.getMarketTray();
        Marble oldExtraMarble = market.getExtraMarble();
        Market newMarker = new Market(market.export());
        Marble[][] newMarketTray = newMarker.getMarketTray();
        Marble newExtraMarble = newMarker.getExtraMarble();
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                assertSame(oldMarketTray[i][j].getWhatIAm(), newMarketTray[i][j].getWhatIAm());
            }
        }
        assertSame(oldExtraMarble.getWhatIAm(), newExtraMarble.getWhatIAm());
    }

}
