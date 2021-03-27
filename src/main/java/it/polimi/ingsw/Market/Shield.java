package it.polimi.ingsw.Market;
import it.polimi.ingsw.Deck.LeaderCard;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.PersonalBoard.Warehouse.DifferentResourceInThisShelfException;
import it.polimi.ingsw.PersonalBoard.Warehouse.PositionAlreadyOccupiedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.ResourceAlreadyPlacedException;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.Resource;

public class Shield extends Marble {
    private final Resource whatIAm = Resource.SHIELD;

    @Override
    public void putResource(WarehouseDepots warehouseDepots, int pos) {
        try {
            warehouseDepots.addResource(whatIAm, pos);
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        } catch (ResourceAlreadyPlacedException e) {
            e.printStackTrace();
        } catch (DifferentResourceInThisShelfException differentResourceInThisShelfException) {
            differentResourceInThisShelfException.printStackTrace();
        }
    }

    @Override
    public void putResource(LeaderCard leaderCard) {
        leaderCard.addResource(whatIAm);
    }

    @Override
    public void putResource(FaithTrack faithTrack) {
        throw new RuntimeException();
    }
}
