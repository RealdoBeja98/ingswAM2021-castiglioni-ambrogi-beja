package it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrackSP;
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
    private FaithTrackSP lorenzoTrack;
    private final int gameIndex;

    /**
     * Constructor method of this class
     * @param gameIndex game index int
     */
    public PersonalBoard(int gameIndex){
        this.gameIndex = gameIndex;
        faithTrack = new FaithTrack(gameIndex);
        warehouseDepots = new WarehouseDepots();
        strongBox = new StrongBox();
        slotsDevelopmentCards = new SlotsDevelopmentCards(gameIndex);
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

    /**
     * This method creates a new faith track for Lorenzo
     */
    public void createFaithTrackSP(){
        lorenzoTrack = new FaithTrackSP(gameIndex);
    }

    /**
     * Getter of the parameter lorenzoTrack
     * @return Lorenzo's faith track, of type FaithTrackSP
     */
    public FaithTrackSP getLorenzoTrack() {
        return lorenzoTrack;
    }

}
