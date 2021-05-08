package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Market.Marbles.Coin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Test class for the class: ProductionPowerLeaderCard
 */
public class WhiteMarbleLeaderCardTest {

    /**
     * This method tests creating a WhiteMarbleLeaderCard from an exported string
     */
    @Test
    void testExportWhiteMarbleLeaderCard(){
        Type[] card1 = {Type.YELLOW, Type.GREEN};
        WhiteMarbleLeaderCard oldWhiteMarbleLeaderCard = new WhiteMarbleLeaderCard(3, card1, new Coin());
        WhiteMarbleLeaderCard newWhiteMarbleLeaderCard = new WhiteMarbleLeaderCard(oldWhiteMarbleLeaderCard.export());
        assertSame(oldWhiteMarbleLeaderCard.getWhatIAm(), newWhiteMarbleLeaderCard.getWhatIAm());
        assertSame(oldWhiteMarbleLeaderCard.getWhiteMarble().getWhatIAm(), newWhiteMarbleLeaderCard.getWhiteMarble().getWhatIAm());
        assertSame(oldWhiteMarbleLeaderCard.getVictoryPoints(), newWhiteMarbleLeaderCard.getVictoryPoints());
        assertSame(oldWhiteMarbleLeaderCard.getCostOfLeaderCard().length, newWhiteMarbleLeaderCard.getCostOfLeaderCard().length);
        for(int i = 0; i < oldWhiteMarbleLeaderCard.getCostOfLeaderCard().length; i++){
            assertSame(oldWhiteMarbleLeaderCard.getCostOfLeaderCard()[i], newWhiteMarbleLeaderCard.getCostOfLeaderCard()[i]);
        }
    }

}
