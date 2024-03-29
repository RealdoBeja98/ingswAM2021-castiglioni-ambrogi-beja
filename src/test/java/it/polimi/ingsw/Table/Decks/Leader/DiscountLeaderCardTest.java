package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the class: Table
 */
public class DiscountLeaderCardTest {

    /**
     * This method tests the getter of the class and their return
     */
    @Test
    void integrityTest(){
        Type[] card1 = {Type.YELLOW, Type.GREEN};
        DiscountLeaderCard discountLeaderCard = new DiscountLeaderCard(2, Resource.SERVANT, card1);
        Resource discountTest = discountLeaderCard.getDiscount();
        Type[] costOfLeaderCardTest = discountLeaderCard.getCostOfLeaderCard();
        LeaderCardType whatIAmTest = discountLeaderCard.getWhatIAm();
        int victoryPointsTest = discountLeaderCard.getVictoryPoints();
        assertNotNull(discountTest);
        assertNotNull(costOfLeaderCardTest[0]);
        assertNotNull(whatIAmTest);
        assertSame(victoryPointsTest, 2);
        discountTest = null;
        costOfLeaderCardTest[0] = null;
        whatIAmTest = null;
        victoryPointsTest = -1;
        assertNull(discountTest);
        assertNull(costOfLeaderCardTest[0]);
        assertNull(whatIAmTest);
        assertSame(victoryPointsTest, -1);
        assertNotNull(discountLeaderCard.getDiscount());
        assertNotNull(discountLeaderCard.getCostOfLeaderCard());
        assertNotNull(discountLeaderCard.getWhatIAm());
        assertSame(discountLeaderCard.getVictoryPoints(), 2);
    }

    /**
     * This method tests creating a DiscountLeaderCard from an exported string
     */
    @Test
    void testExportDiscountLeaderCard(){
        Type[] card1 = {Type.YELLOW, Type.GREEN};
        DiscountLeaderCard oldDiscountLeaderCard = new DiscountLeaderCard(2, Resource.SERVANT, card1);
        DiscountLeaderCard newDiscountLeaderCard = new DiscountLeaderCard(oldDiscountLeaderCard.export());
        assertSame(oldDiscountLeaderCard.getWhatIAm(), newDiscountLeaderCard.getWhatIAm());
        assertSame(oldDiscountLeaderCard.getVictoryPoints(), newDiscountLeaderCard.getVictoryPoints());
        assertSame(oldDiscountLeaderCard.getCostOfLeaderCard().length, newDiscountLeaderCard.getCostOfLeaderCard().length);
        for(int i = 0; i < oldDiscountLeaderCard.getCostOfLeaderCard().length; i++){
            assertSame(oldDiscountLeaderCard.getCostOfLeaderCard()[i], newDiscountLeaderCard.getCostOfLeaderCard()[i]);
        }
        assertSame(oldDiscountLeaderCard.getDiscount(), newDiscountLeaderCard.getDiscount());
    }

}
