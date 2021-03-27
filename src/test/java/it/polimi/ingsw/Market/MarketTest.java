package it.polimi.ingsw.Market;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MarketTest {

    /**
     * We are testing the market when we choose a column
     */
    @Test
    public void testMarketChooseColumn(){
        Market market = new Market();
        Marble[][] oldMarketTray = market.getMarketTray();
        Marble oldExtraMarble = market.getExtraMarble();
        Marble[] choosenMarbles = market.chooseColumn(1);
        assertSame(choosenMarbles[0], oldMarketTray[0][1]);
        assertSame(choosenMarbles[1], oldMarketTray[1][1]);
        assertSame(choosenMarbles[2], oldMarketTray[2][1]);
        assertSame(market.getExtraMarble(), oldMarketTray[0][1]);
        assertSame(oldExtraMarble, market.getMarketTray()[2][1]);
        assertSame(choosenMarbles[0], market.getExtraMarble());
        assertSame(choosenMarbles[1], market.getMarketTray()[0][1]);
        assertSame(choosenMarbles[2], market.getMarketTray()[1][1]);
    }

    /**
     * We are testing the market when we choose a row
     */
    @Test
    public void testMarketChooseRow(){
        Market market = new Market();
        Marble[][] oldMarketTray = market.getMarketTray();
        Marble oldExtraMarble = market.getExtraMarble();
        Marble[] choosenMarbles = market.chooseRow(2);
        assertSame(choosenMarbles[0], oldMarketTray[2][0]);
        assertSame(choosenMarbles[1], oldMarketTray[2][1]);
        assertSame(choosenMarbles[2], oldMarketTray[2][2]);
        assertSame(choosenMarbles[3], oldMarketTray[2][3]);
        assertSame(market.getExtraMarble(), oldMarketTray[2][0]);
        assertSame(oldExtraMarble, market.getMarketTray()[2][3]);
        assertSame(choosenMarbles[0], market.getExtraMarble());
        assertSame(choosenMarbles[1], market.getMarketTray()[2][0]);
        assertSame(choosenMarbles[2], market.getMarketTray()[2][1]);
        assertSame(choosenMarbles[3], market.getMarketTray()[2][2]);
    }

    /**
     * We are testing the market when we choose both a row and a column
     */
    @Test
    public void testMarketChooseRowAndColumn(){
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

}
