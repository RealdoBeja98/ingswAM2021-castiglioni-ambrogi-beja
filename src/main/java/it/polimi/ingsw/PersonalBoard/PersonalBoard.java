package it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.PersonalBoard.SlotsDevelopmentCards.SlotsDevelopmentCards;
import it.polimi.ingsw.PersonalBoard.StrongBox.StrongBox;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;

public class PersonalBoard {

    private FaithTrack faithTrack;
    private WarehouseDepots warehouseDepots;
    private StrongBox strongBox;
    private SlotsDevelopmentCards slotsDevelopmentCards;

    public PersonalBoard(){
        faithTrack = new FaithTrack(Game.getInstance().players);
        warehouseDepots = new WarehouseDepots();
        strongBox = new StrongBox();
        slotsDevelopmentCards = new SlotsDevelopmentCards();
    }

    public WarehouseDepots getWarehouseDepots() {
        return warehouseDepots;
    }

    public StrongBox getStrongBox() {
        return strongBox;
    }

    public SlotsDevelopmentCards getSlotsDevelopmentCards() {
        return slotsDevelopmentCards;
    }

    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

}
