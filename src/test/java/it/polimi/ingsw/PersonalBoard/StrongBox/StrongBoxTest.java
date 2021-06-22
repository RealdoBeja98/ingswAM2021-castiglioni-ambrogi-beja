package it.polimi.ingsw.PersonalBoard.StrongBox;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.NegativeResourceException;
import it.polimi.ingsw.Exceptions.NotAResourceForStrongBoxException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the class: StrongBox
 */
public class StrongBoxTest {

    /**
     * This method tests the addition of resources in the strongbox
     */
    @Test
    void addTest(){
        StrongBox strongBox = new StrongBox();
        try {
            strongBox.add(Resource.COIN, 10);
        } catch (NotAResourceForStrongBoxException e) {
            fail();
        }
        assertSame(strongBox.getCoin(),10);
        assertThrows(NotAResourceForStrongBoxException.class, () -> strongBox.add(Resource.FAITH, 10));
    }

    /**
     * This method tests the subtraction of resources from the strongbox
     */
    @Test
    void removeTest(){
        StrongBox strongBox = new StrongBox();
        try {
            strongBox.add(Resource.COIN, 10);
        } catch (NotAResourceForStrongBoxException e) {
            fail();
        }
        assertSame(strongBox.getCoin(),10);
        try {
            strongBox.remove(Resource.COIN, 5);
        } catch (NegativeResourceException | NotAResourceForStrongBoxException e) {
            fail();
        }
        assertSame(strongBox.getCoin(),5);
        assertThrows(NegativeResourceException.class, () -> strongBox.remove(Resource.COIN, 10));
        try {
            strongBox.remove(Resource.COIN, 5);
        } catch (NegativeResourceException | NotAResourceForStrongBoxException e) {
            fail();
        }
        assertSame(strongBox.getCoin(),0);
        assertThrows(NotAResourceForStrongBoxException.class, () -> strongBox.remove(Resource.FAITH, 10));
    }

    /**
     * This method tests exporting a StrongBox
     */
    @Test
    void export(){
        StrongBox strongBox = new StrongBox();
        try {
            strongBox.add(Resource.COIN, 10);
            strongBox.add(Resource.STONE, 20);
            strongBox.add(Resource.SERVANT, 30);
            strongBox.add(Resource.SHIELD, 40);
        } catch (NotAResourceForStrongBoxException e) {
            fail();
        }
        StrongBox newStrongBox = new StrongBox(strongBox.export());
        assertSame(strongBox.getCoin(),newStrongBox.getCoin());
        assertSame(strongBox.getStone(),newStrongBox.getStone());
        assertSame(strongBox.getServant(),newStrongBox.getServant());
        assertSame(strongBox.getShield(),newStrongBox.getShield());
        assertTrue(strongBox.export().equals(newStrongBox.export()));
    }

}
