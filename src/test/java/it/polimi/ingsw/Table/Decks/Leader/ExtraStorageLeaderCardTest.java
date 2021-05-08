package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Exceptions.EmptySlotExtraStorageLeaderCardException;
import it.polimi.ingsw.Exceptions.OccupiedSlotExtraStorageLeaderCardException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the class: ExtraStorageLeaderCard
 */
public class ExtraStorageLeaderCardTest {

    /**
     * This method tests the addition and subtraction of resources from an extra storage leader card
     */
    @Test
    void addRemoveResourceFromExtraStorageLeaderCardTest(){
        ExtraStorageLeaderCard extraStorageLeaderCard = new ExtraStorageLeaderCard(3, Resource.COIN, Resource.STONE);
        assertSame(extraStorageLeaderCard.getVictoryPoints(), 3);
        assertSame(extraStorageLeaderCard.getCostOfLeaderCard(), Resource.COIN);
        assertSame(extraStorageLeaderCard.getStorageType(), Resource.STONE);
        assertSame(extraStorageLeaderCard.occupiedResources(), 0);
        assertThrows(EmptySlotExtraStorageLeaderCardException.class, extraStorageLeaderCard::removeResource);
        try {
            extraStorageLeaderCard.addResource();
        } catch (OccupiedSlotExtraStorageLeaderCardException e) {
            e.printStackTrace();
        }
        assertSame(extraStorageLeaderCard.occupiedResources(), 1);
        try {
            extraStorageLeaderCard.addResource();
        } catch (OccupiedSlotExtraStorageLeaderCardException e) {
            e.printStackTrace();
        }
        assertSame(extraStorageLeaderCard.occupiedResources(), 2);
        assertThrows(OccupiedSlotExtraStorageLeaderCardException.class, extraStorageLeaderCard::addResource);
        try {
            extraStorageLeaderCard.removeResource();
        } catch (EmptySlotExtraStorageLeaderCardException e) {
            e.printStackTrace();
        }
        assertSame(extraStorageLeaderCard.occupiedResources(), 1);
    }

    /**
     * This method tests creating a ExtraStorageLeaderCard from an exported string
     */
    @Test
    void testExportExtraStorageLeaderCard(){
        ExtraStorageLeaderCard oldExtraStorageLeaderCard = new ExtraStorageLeaderCard(3, Resource.COIN, Resource.STONE);
        ExtraStorageLeaderCard newExtraStorageLeaderCard = new ExtraStorageLeaderCard(oldExtraStorageLeaderCard.export());
        assertSame(oldExtraStorageLeaderCard.getWhatIAm(), newExtraStorageLeaderCard.getWhatIAm());
        assertSame(oldExtraStorageLeaderCard.getStorageType(), newExtraStorageLeaderCard.getStorageType());
        assertSame(oldExtraStorageLeaderCard.getCostOfLeaderCard(), newExtraStorageLeaderCard.getCostOfLeaderCard());
        assertSame(oldExtraStorageLeaderCard.getVictoryPoints(), newExtraStorageLeaderCard.getVictoryPoints());
    }

}
