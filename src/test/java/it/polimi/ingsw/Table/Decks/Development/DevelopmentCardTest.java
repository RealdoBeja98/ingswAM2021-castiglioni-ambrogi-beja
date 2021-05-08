package it.polimi.ingsw.Table.Decks.Development;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Decks.DevelopmentDeck;
import it.polimi.ingsw.Table.Decks.ResourceProduction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Test class for the class: DevelopmentCard
 */
public class DevelopmentCardTest {

    /**
     * This method tests the creation of a ResourceProduction from a DevelopmentCard
     */
    @Test
    void resourceProductionTest(){
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

    /**
     * This method tests creating a DevelopmentCard from an exported string
     */
    @Test
    void testExportDevelopmentCard(){
        Resource[] cost = {Resource.COIN, Resource.SERVANT, Resource.SHIELD};
        int[] costNumber = {1, 2, 3};
        Resource[] requirements = {Resource.COIN};
        int[] costRequirements = {3};
        Resource[] products = {Resource.FAITH, Resource.SERVANT};
        int[] costProducts = {2,1};
        DevelopmentCard oldDevelopmentCard = new DevelopmentCard(cost, costNumber, Type.GREEN, 2,
                requirements, costRequirements, products, costProducts, 5);
        DevelopmentCard newDevelopmentCard = new DevelopmentCard(oldDevelopmentCard.export());
        assertSame(oldDevelopmentCard.getCost().length, newDevelopmentCard.getCost().length);
        assertSame(oldDevelopmentCard.getCostNumber().length, newDevelopmentCard.getCostNumber().length);
        assertSame(oldDevelopmentCard.getCost().length, oldDevelopmentCard.getCostNumber().length);
        for(int i = 0; i < oldDevelopmentCard.getCost().length; i++){
            assertSame(oldDevelopmentCard.getCost()[i], newDevelopmentCard.getCost()[i]);
            assertSame(oldDevelopmentCard.getCostNumber()[i], newDevelopmentCard.getCostNumber()[i]);
        }
        assertSame(oldDevelopmentCard.getType(), newDevelopmentCard.getType());
        assertSame(oldDevelopmentCard.getLevel(), newDevelopmentCard.getLevel());

        assertSame(oldDevelopmentCard.getRequirements().length, newDevelopmentCard.getRequirements().length);
        assertSame(oldDevelopmentCard.getCostRequirements().length, newDevelopmentCard.getCostRequirements().length);
        assertSame(oldDevelopmentCard.getRequirements().length, oldDevelopmentCard.getCostRequirements().length);
        for(int i = 0; i < oldDevelopmentCard.getRequirements().length; i++){
            assertSame(oldDevelopmentCard.getRequirements()[i], newDevelopmentCard.getRequirements()[i]);
            assertSame(oldDevelopmentCard.getCostRequirements()[i], newDevelopmentCard.getCostRequirements()[i]);
        }
        assertSame(oldDevelopmentCard.getProducts().length, newDevelopmentCard.getProducts().length);
        assertSame(oldDevelopmentCard.getCostProducts().length, newDevelopmentCard.getCostProducts().length);
        assertSame(oldDevelopmentCard.getProducts().length, oldDevelopmentCard.getCostProducts().length);
        for(int i = 0; i < oldDevelopmentCard.getProducts().length; i++){
            assertSame(oldDevelopmentCard.getProducts()[i], newDevelopmentCard.getProducts()[i]);
            assertSame(oldDevelopmentCard.getCostProducts()[i], newDevelopmentCard.getCostProducts()[i]);
        }
        assertSame(oldDevelopmentCard.getVictoryPoints(), newDevelopmentCard.getVictoryPoints());
    }

}
