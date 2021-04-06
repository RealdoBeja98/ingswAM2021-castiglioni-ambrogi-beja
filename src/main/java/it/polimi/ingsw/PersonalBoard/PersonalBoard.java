package it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.PersonalBoard.SlotsDevelopmentCards.SlotsDevelopmentCards;
import it.polimi.ingsw.PersonalBoard.StrongBox.StrongBox;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;

/**
 * This Class contains all the elements of a player board
 */
public class PersonalBoard {

    private final FaithTrack faithTrack;
    private final WarehouseDepots warehouseDepots;
    private final StrongBox strongBox;
    private final SlotsDevelopmentCards slotsDevelopmentCards;

    /**
     * Constructor method of this class
     */
    public PersonalBoard(){
        faithTrack = new FaithTrack(Game.getInstance().players);
        warehouseDepots = new WarehouseDepots();
        strongBox = new StrongBox();
        slotsDevelopmentCards = new SlotsDevelopmentCards();
    }

    /**
     * Getter of the parameter warehouseDepots
     * @return the player warehouseDepots, of type WarehouseDepots
     */
    public WarehouseDepots getWarehouseDepots() {
        return warehouseDepots;
    }

    /**
     * Getter of the parameter strongBox
     * @return the player strongBox, of type StrongBox
     */
    public StrongBox getStrongBox() {
        return strongBox;
    }

    /**
     * Getter of the parameter slotsDevelopmentCards
     * @return the player slotsDevelopmentCards, of type SlotsDevelopmentCards
     */
    public SlotsDevelopmentCards getSlotsDevelopmentCards() {
        return slotsDevelopmentCards;
    }

    /**
     * Getter of the parameter faithTrack
     * @return the player faithTrack, of type FaithTrack
     */
    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

}
