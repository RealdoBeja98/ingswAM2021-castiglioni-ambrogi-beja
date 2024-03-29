package it.polimi.ingsw.PersonalBoard.SlotsDevelopmentCards;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import it.polimi.ingsw.Exceptions.IndexOutOfSlotDevelopmentCardsException;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import it.polimi.ingsw.Exceptions.PositionInvalidException;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import org.junit.jupiter.api.Test;

/**
 * Test class for the class: SlotsDevelopmentCards
 */
public class SlotsDevelopmentCardsTest {

    /**
     * This method initializes a development card
     * @param level: level of the card
     * @return a development card, of type DevelopmentCard
     */
    private DevelopmentCard exampleDevelopmentCard(int level){
        Resource[] cost = {Resource.COIN, Resource.SERVANT, Resource.SHIELD};
        int[] costNumber = {1,2,3};
        Resource[] requirements = {Resource.COIN, Resource.SERVANT};
        int[] costRequirements = {2,1};
        Resource[] products = {Resource.COIN, Resource.FAITH};
        int[] costProducts = {2,2};
        return new DevelopmentCard(cost, costNumber, Type.BLUE, level, requirements, costRequirements, products, costProducts, 3);
    }

    /**
     * This method tests the addition of cards to their slot
     */
    @Test
    void testAddDevelopmentCard() throws PositionInvalidException {
        SlotsDevelopmentCards slots = new SlotsDevelopmentCards(40);
        DevelopmentCard card1 = exampleDevelopmentCard(1);
        DevelopmentCard card2 = exampleDevelopmentCard(1);
        DevelopmentCard card3 = exampleDevelopmentCard(1);
        DevelopmentCard card4 = exampleDevelopmentCard(2);
        DevelopmentCard card5 = exampleDevelopmentCard(2);
        DevelopmentCard card6 = exampleDevelopmentCard(3);
        try {
            slots.addDevelopmentCard(1, card1);
        } catch (IndexOutOfSlotDevelopmentCardsException e) {
            fail();
        }
        assertSame(card1, slots.getActiveCards()[0]);
        assertSame(card1,slots.getSlot()[2][0]);
        assertSame(3, slots.victoryPoints());
        try {
            slots.addDevelopmentCard(2, card2);
            slots.addDevelopmentCard(3, card3);
            slots.addDevelopmentCard(2, card4);
            slots.addDevelopmentCard(3, card5);
            slots.addDevelopmentCard(2, card6);
        } catch (IndexOutOfSlotDevelopmentCardsException e) {
            fail();
        }
        assertSame(card1, slots.getActiveCards()[0]);
        assertSame(card6, slots.getActiveCards()[1]);
        assertSame(card5, slots.getActiveCards()[2]);
        assertSame(card1,slots.getSlot()[2][0]);
        assertSame(card2,slots.getSlot()[2][1]);
        assertSame(card3,slots.getSlot()[2][2]);
        assertSame(card4,slots.getSlot()[1][1]);
        assertSame(card5,slots.getSlot()[1][2]);
        assertSame(card6,slots.getSlot()[0][1]);
        assertSame(18, slots.victoryPoints());
    }

    /**
     * This method tests the exception during the addition of cards to their slot
     */
    @Test
    void testExceptionsAddDevelopmentCard() throws PositionInvalidException {
        SlotsDevelopmentCards slots = new SlotsDevelopmentCards(41);
        DevelopmentCard card1 = exampleDevelopmentCard(1);
        DevelopmentCard card2 = exampleDevelopmentCard(1);
        DevelopmentCard card3 = exampleDevelopmentCard(2);
        DevelopmentCard card4 = exampleDevelopmentCard(3);
        DevelopmentCard card5 = exampleDevelopmentCard(2);
        try {
            slots.addDevelopmentCard(1,card1);
        } catch (IndexOutOfSlotDevelopmentCardsException e) {
            fail();
        }
        assertThrows(PositionInvalidException.class, () -> slots.addDevelopmentCard(1,card2));
        try {
            slots.addDevelopmentCard(2,card2);
            slots.addDevelopmentCard(2,card3);
        } catch (IndexOutOfSlotDevelopmentCardsException e) {
            fail();
        }
        assertThrows(PositionInvalidException.class, () -> slots.addDevelopmentCard(3,card4));
        assertThrows(PositionInvalidException.class, () -> slots.addDevelopmentCard(1,card4));
        try {
            slots.addDevelopmentCard(2,card4);
        } catch (IndexOutOfSlotDevelopmentCardsException e) {
            fail();
        }
        assertThrows(PositionInvalidException.class, () -> slots.addDevelopmentCard(3,card5));
    }

    /**
     * This method tests the exception out of bound during the addition of cards to their slot
     */
    @Test
    void testOutOfBoundExceptionsAddDevelopmentCard(){
        SlotsDevelopmentCards slots = new SlotsDevelopmentCards(42);
        DevelopmentCard card1 = exampleDevelopmentCard(1);
        assertThrows(IndexOutOfSlotDevelopmentCardsException.class, () -> slots.addDevelopmentCard(0,card1));
        assertThrows(IndexOutOfSlotDevelopmentCardsException.class, () -> slots.addDevelopmentCard(4,card1));
    }

    /**
     * This method tests the getter of the class and their return
     */
    @Test
    void integrityTest(){
        SlotsDevelopmentCards slots = new SlotsDevelopmentCards(43);
        DevelopmentCard[][] test = slots.getSlot();
        assertNull(slots.getSlot()[0][0]);
        assertNull(slots.getActiveCards()[0]);
        test[0][0] = exampleDevelopmentCard(1);
        assertNull(slots.getSlot()[0][0]);
        DevelopmentCard[] test1 = slots.getActiveCards();
        test1[0] = exampleDevelopmentCard(1);
        assertNull(slots.getActiveCards()[0]);
    }

    /**
     * This method tests exporting a StrongBox
     */
    @Test
    void exportTest(){
        SlotsDevelopmentCards slots = new SlotsDevelopmentCards();
        DevelopmentCard[][] test = slots.getSlot();
        test[0][0] = exampleDevelopmentCard(1);
        test[0][2] = null;
        test[1][0] = exampleDevelopmentCard(2);
        test[1][1] = exampleDevelopmentCard(1);
        test[1][2] = exampleDevelopmentCard(1);
        test[2][0] = exampleDevelopmentCard(2);
        test[2][1] = exampleDevelopmentCard(3);
        test[2][2] = exampleDevelopmentCard(1);
        SlotsDevelopmentCards newSlotsDevelopmentCards = new SlotsDevelopmentCards(slots.export());
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                assertSame(slots.getSlot()[i][j], newSlotsDevelopmentCards.getSlot()[i][j]);
            }
        }
        assertTrue(slots.export().equals(newSlotsDevelopmentCards.export()));
    }

}
