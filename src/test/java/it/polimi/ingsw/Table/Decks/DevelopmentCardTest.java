package it.polimi.ingsw.Table.Decks;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Test class for the class: DevelopmentCard
 */
public class DevelopmentCardTest {

    /**
     * This method tests the creation of a DevelopmentCard
     */
    @Test
    public void integrityTest(){
        Resource[] cost = {Resource.COIN, Resource.SERVANT, Resource.SHIELD};
        int[] costNumber = {1, 2, 3};
        Resource[] requirements = {Resource.COIN};
        int[] costRequirements = {3};
        Resource[] products = {Resource.FAITH, Resource.SERVANT};
        int[] costProducts = {2,1};
        DevelopmentCard developmentCard = new DevelopmentCard(cost, costNumber, Type.GREEN, 2,
                requirements, costRequirements, products, costProducts, 5);
        assertSame(cost,developmentCard.getCost());
        assertSame(costNumber,developmentCard.getCostNumber());
        assertSame(Type.GREEN,developmentCard.getType());
        assertSame(2,developmentCard.getLevel());
        assertSame(requirements,developmentCard.getRequirements());
        assertSame(costRequirements,developmentCard.getCostRequirements());
        assertSame(products,developmentCard.getProducts());
        assertSame(costProducts,developmentCard.getCostProducts());
        assertSame(5,developmentCard.getVictoryPoints());
    }

    /**
     * This method tests the creation of a ResourceProduction from a DevelopmentCard
     */
    @Test
    public void resourceProductionTest(){
        Resource[] cost = {Resource.COIN, Resource.SERVANT, Resource.SHIELD};
        int[] costNumber = {1, 2, 3};
        Resource[] requirements = {Resource.COIN};
        int[] costRequirements = {3};
        Resource[] products = {Resource.FAITH, Resource.SERVANT};
        int[] costProducts = {2,1};
        DevelopmentCard developmentCard = new DevelopmentCard(cost, costNumber, Type.GREEN, 2,
                requirements, costRequirements, products, costProducts, 5);
        ResourceProduction resourceProduction = developmentCard.resourceProduction();
        assertSame(3, resourceProduction.getRequiredCoin());
        assertSame(0, resourceProduction.getRequiredStone());
        assertSame(0, resourceProduction.getRequiredShield());
        assertSame(0, resourceProduction.getRequiredServant());
        assertSame(0, resourceProduction.getRequiredGeneric());
        assertSame(2,resourceProduction.getProductionFaith());
        assertSame(1,resourceProduction.getProductionServant());
        assertSame(0,resourceProduction.getProductionCoin());
        assertSame(0,resourceProduction.getProductionStone());
        assertSame(0,resourceProduction.getProductionShield());
        assertSame(0,resourceProduction.getProductionGeneric());
    }

}
