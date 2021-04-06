package it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.NegativeResourceException;
import it.polimi.ingsw.Exceptions.NotAResourceForStrongBoxException;
import it.polimi.ingsw.PersonalBoard.StrongBox.StrongBox;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StrongBoxTest {
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
