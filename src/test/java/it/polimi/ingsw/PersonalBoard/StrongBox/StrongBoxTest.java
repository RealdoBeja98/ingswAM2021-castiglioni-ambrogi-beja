package it.polimi.ingsw.PersonalBoard.StrongBox;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.NegativeResourceException;
import it.polimi.ingsw.Exceptions.NotAResourceForStrongBoxException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the class: StrongBox
 */
public class StrongBoxTest {

    /**
     * This method tests the addition of resources in the strongbox
     */
    @Test
    public void addTest(){
        StrongBox strongBox = new StrongBox();
        try {
            strongBox.add(Resource.COIN, 10);
        } catch (NotAResourceForStrongBoxException e) {
            e.printStackTrace();
        }
        assertSame(strongBox.getCoin(),10);
        assertThrows(NotAResourceForStrongBoxException.class, () ->{strongBox.add(Resource.FAITH, 10);});
    }

    /**
     * This method tests the subtraction of resources from the strongbox
     */
    @Test
    public void removeTest(){
        StrongBox strongBox = new StrongBox();
        try {
            strongBox.add(Resource.COIN, 10);
        } catch (NotAResourceForStrongBoxException e) {
            e.printStackTrace();
        }
        assertSame(strongBox.getCoin(),10);
        try {
            strongBox.remove(Resource.COIN, 5);
        } catch (NegativeResourceException | NotAResourceForStrongBoxException e) {
            e.printStackTrace();
        }
        assertSame(strongBox.getCoin(),5);
        assertThrows(NegativeResourceException.class, () ->{strongBox.remove(Resource.COIN, 10);});
        try {
            strongBox.remove(Resource.COIN, 5);
        } catch (NegativeResourceException | NotAResourceForStrongBoxException e) {
            e.printStackTrace();
        }
        assertSame(strongBox.getCoin(),0);
        assertThrows(NotAResourceForStrongBoxException.class, () ->{strongBox.remove(Resource.FAITH, 10);});
    }
}
