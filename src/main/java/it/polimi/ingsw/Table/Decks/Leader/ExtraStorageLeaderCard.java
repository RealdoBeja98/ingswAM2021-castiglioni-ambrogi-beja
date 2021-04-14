package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Exceptions.EmptySlotExtraStorageLeaderCardException;
import it.polimi.ingsw.Exceptions.OccupiedSlotExtraStorageLeaderCardException;
import it.polimi.ingsw.Enums.Resource;

/**
 * This Class represents the leader card that give extra storage
 */
public class ExtraStorageLeaderCard extends LeaderCard {

    private final Resource costOfLeaderCard;
    private final Boolean[] extraStorage = {false, false};
    private final Resource storageType;

    /**
     * Constructor method of this class
     */
    public ExtraStorageLeaderCard(int victoryPoints, Resource costOfLeaderCard, Resource storageType) {
        this.whatIAm = LeaderCardType.STORAGE;
        this.victoryPoints = victoryPoints;
        this.costOfLeaderCard = costOfLeaderCard;
        this.storageType = storageType;
    }

    /**
     * Getter of the parameter costOfLeaderCard
     * @return the cost of the card, of type Resource
     */
    public Resource getCostOfLeaderCard() {
        return costOfLeaderCard;
    }

    /**
     * Getter of the parameter storageType
     * @return the storage type of the card, of type Resource
     */
    @Override
    public Resource getStorageType() {
        return storageType;
    }

    /**
     * This method counts how many slot in the card are occupied
     * @return the number of occupied slots, of type int
     */
    @Override
    public int occupiedResources(){
        int result = 0;

        for(Boolean i : extraStorage){
            if(i){
                result++;
            }
        }
        return result;
    }

    /**
     * This method adds a resource to the card
     * @throws OccupiedSlotExtraStorageLeaderCardException if all the slots in the card are occupied
     */
    @Override
    public void addResource() throws OccupiedSlotExtraStorageLeaderCardException {
        int occupied = occupiedResources();
        if(occupied == 2){
            throw new OccupiedSlotExtraStorageLeaderCardException();
        }
        else if(occupied == 1){
            extraStorage[1] = true;
        }
        else{
            extraStorage[0] = true;
        }
    }

    /**
     * This method removes a resource from the card
     * @throws EmptySlotExtraStorageLeaderCardException if the card is already empty
     */
    public void removeResource() throws EmptySlotExtraStorageLeaderCardException {
        int occupied = occupiedResources();
        if(occupied == 0){
            throw new EmptySlotExtraStorageLeaderCardException();
        }
        else if(occupied == 1){
            extraStorage[0] = false;
        }
        else{
            extraStorage[1] = false;
        }
    }
}
