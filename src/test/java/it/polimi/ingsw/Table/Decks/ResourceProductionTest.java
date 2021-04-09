package it.polimi.ingsw.Table.Decks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the class: ResourceProduction
 */
public class ResourceProductionTest {

    /**
     * This method tests the creation of a ResourceProduction
     */
    @Test
    public void integrityTest(){
        ResourceProduction x = new ResourceProduction(1,2,3,
                4,5,6,7,8,
                9,10,11);
        assertSame(1,x.getRequiredCoin());
        assertSame(2,x.getRequiredServant());
        assertSame(3,x.getRequiredShield());
        assertSame(4,x.getRequiredStone());
        assertSame(5,x.getRequiredGeneric());
        assertSame(6,x.getProductionCoin());
        assertSame(7,x.getProductionServant());
        assertSame(8,x.getProductionShield());
        assertSame(9,x.getProductionStone());
        assertSame(10,x.getProductionGeneric());
        assertSame(11,x.getProductionFaith());
    }

    /**
     * This method tests the sum of ResourceProduction
     */
    @Test
    public void sumOfResourceProduction(){
        ResourceProduction x = new ResourceProduction(1,2,3,
                4,5,6,7,8,
                9,10,11);
        assertSame(1,x.getRequiredCoin());
        ResourceProduction y = new ResourceProduction(2,3,0,
                4,5,6,7,8,
                9,10,18);
        ResourceProduction sum = x.sum(y);
        assertSame(3,sum.getRequiredCoin());
        assertSame(5,sum.getRequiredServant());
        assertSame(3,sum.getRequiredShield());
        assertSame(8,sum.getRequiredStone());
        assertSame(10,sum.getRequiredGeneric());
        assertSame(12,sum.getProductionCoin());
        assertSame(14,sum.getProductionServant());
        assertSame(16,sum.getProductionShield());
        assertSame(18,sum.getProductionStone());
        assertSame(20,sum.getProductionGeneric());
        assertSame(29,sum.getProductionFaith());
    }

}
