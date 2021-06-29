package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Exceptions.EmptySlotExtraStorageLeaderCardException;
import it.polimi.ingsw.Exceptions.OccupiedSlotExtraStorageLeaderCardException;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Utilities.ImportExport;

/**
 * This Class represents the leader card that give extra storage
 */
public class ExtraStorageLeaderCard extends LeaderCard {

    private final Resource costOfLeaderCard;
    private final Boolean[] extraStorage = {false, false};
    private final Resource storageType;

    /**
     * Constructor method of this class
     * @param costOfLeaderCard cost of leader card
     * @param storageType type of storage
     * @param victoryPoints victory points
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
    public Resource getStorageType() {
        return storageType;
    }

    /**
     * This method counts how many slot in the card are occupied
     * @return the number of occupied slots, of type int
     */
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

    /**
     * This method exports the ExtraStorageLeaderCard to a String
     * @return a string with all the card data, of type String
     */
    @Override
    public String export() {
        String result = "E";
        String fromVictoryPoints = String.valueOf(victoryPoints);
        String fromCostOfLeaderCard = ImportExport.exportResource(costOfLeaderCard);
        String fromStorageType = ImportExport.exportResource(storageType);
        result = new StringBuilder().append(result).append(fromVictoryPoints).append(fromCostOfLeaderCard).append(fromStorageType).toString();
        return result;
    }

    /**
     * Constructor method of this class from the exported string
     * @param importedString: the string to import
     */
    public ExtraStorageLeaderCard(String importedString) {
        this.whatIAm = LeaderCardType.STORAGE;
        victoryPoints = Integer.parseInt(importedString.substring(1, 2));
        String toAddCostOfLeaderCard = importedString.substring(2, 3);
        costOfLeaderCard = ImportExport.importResource(toAddCostOfLeaderCard);
        String toAddStorageType = importedString.substring(3, 4);
        storageType = ImportExport.importResource(toAddStorageType);
    }

}
