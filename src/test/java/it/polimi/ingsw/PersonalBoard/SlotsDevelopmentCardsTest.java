package it.polimi.ingsw.PersonalBoard;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import it.polimi.ingsw.Deck.DevelopmentCard;
import it.polimi.ingsw.PersonalBoard.SlotsDevelopmentCards.PositionInvalidException;
import it.polimi.ingsw.PersonalBoard.SlotsDevelopmentCards.SlotsDevelopmentCards;
import it.polimi.ingsw.PersonalBoard.Warehouse.PositionAlreadyOccupiedException;
import it.polimi.ingsw.Resource;
import it.polimi.ingsw.Type;
import org.junit.jupiter.api.Test;

public class SlotsDevelopmentCardsTest {

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
     * We are testing adding some development cards
     */
    @Test
    public void testAddDevelopmentCard(){
        SlotsDevelopmentCards slots = new SlotsDevelopmentCards();
        DevelopmentCard card1 = exampleDevelopmentCard(1);
        DevelopmentCard card2 = exampleDevelopmentCard(1);
        DevelopmentCard card3 = exampleDevelopmentCard(1);
        DevelopmentCard card4 = exampleDevelopmentCard(2);
        DevelopmentCard card5 = exampleDevelopmentCard(2);
        DevelopmentCard card6 = exampleDevelopmentCard(3);

        slots.addDevelopmentCard(1, card1);
        assertSame(card1, slots.getActiveCards()[0]);
        assertSame(card1,slots.getSlot()[2][0]);
        assertSame(3, slots.victoryPoints());

        slots.addDevelopmentCard(2, card2);
        slots.addDevelopmentCard(3, card3);
        slots.addDevelopmentCard(2, card4);
        slots.addDevelopmentCard(3, card5);
        slots.addDevelopmentCard(2, card6);

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
     * We are testing exceptions while adding some development cards
     */
    @Test
    public void testExceptionsAddDevelopmentCard(){
        SlotsDevelopmentCards slots = new SlotsDevelopmentCards();
        DevelopmentCard card1 = exampleDevelopmentCard(1);
        DevelopmentCard card2 = exampleDevelopmentCard(1);
        DevelopmentCard card3 = exampleDevelopmentCard(2);
        DevelopmentCard card4 = exampleDevelopmentCard(3);
        DevelopmentCard card5 = exampleDevelopmentCard(2);
        slots.addDevelopmentCard(1,card1);
        slots.addDevelopmentCard(1,card2);
        slots.addDevelopmentCard(2,card2);
        slots.addDevelopmentCard(2,card3);
        slots.addDevelopmentCard(3,card4);
        slots.addDevelopmentCard(1,card4);
        slots.addDevelopmentCard(2,card4);
        slots.addDevelopmentCard(2,card5);
        slots.addDevelopmentCard(3,card5);
    }

    /**
     * We are testing exceptions while adding some development cards out of bound
     */
    @Test
    public void testOutOfBoundExceptionsAddDevelopmentCard(){
        SlotsDevelopmentCards slots = new SlotsDevelopmentCards();
        DevelopmentCard card1 = exampleDevelopmentCard(1);
        DevelopmentCard card2 = exampleDevelopmentCard(1);
        assertThrows(IndexOutOfBoundsException.class, () ->{slots.addDevelopmentCard(0,card1);});
        assertThrows(IndexOutOfBoundsException.class, () ->{slots.addDevelopmentCard(4,card1);});
    }

}
