package it.polimi.ingsw.Table.Decks.Leader;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Decks.ResourceProduction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Test class for the class: ProductionPowerLeaderCard
 */
public class ProductionPowerLeaderCardTest {

    /**
     * This method tests the creation of a ProductionPowerLeaderCard
     */
    @Test
    public void integrityTest(){
        ProductionPowerLeaderCard productionPowerLeaderCard = new ProductionPowerLeaderCard(3, Type.BLUE, Resource.SERVANT);
        assertSame(productionPowerLeaderCard.getVictoryPoints(), 3);
        assertSame(productionPowerLeaderCard.getRequirement(), Resource.SERVANT);
        assertSame(productionPowerLeaderCard.getCostOfLeaderCard(), Type.BLUE);
    }

    /**
     * This method tests the creation of a ResourceProduction from a ProductionPowerLeaderCard
     */
    @Test
    public void ProductionPowerLeaderCardTest(){
        ProductionPowerLeaderCard productionPowerLeaderCard = new ProductionPowerLeaderCard(3, Type.BLUE, Resource.SERVANT);
        ResourceProduction resourceProduction = productionPowerLeaderCard.resourceProduction();
        assertSame(resourceProduction.getRequiredCoin(), 0);
        assertSame(resourceProduction.getRequiredServant(), 1);
        assertSame(resourceProduction.getRequiredShield(), 0);
        assertSame(resourceProduction.getRequiredStone(), 0);
        assertSame(resourceProduction.getRequiredGeneric(), 0);
        assertSame(resourceProduction.getProductionCoin(), 0);
        assertSame(resourceProduction.getProductionServant(), 0);
        assertSame(resourceProduction.getProductionShield(), 0);
        assertSame(resourceProduction.getProductionStone(), 0);
        assertSame(resourceProduction.getProductionGeneric(), 1);
        assertSame(resourceProduction.getProductionFaith(), 1);
    }

}
