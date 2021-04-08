package it.polimi.ingsw.Table.Decks.Leader;

import it.polimi.ingsw.Exceptions.EmptySlotExtraStorageLeaderCardException;
import it.polimi.ingsw.Exceptions.OccupiedSlotExtraStorageLeaderCardException;
import it.polimi.ingsw.Enums.Resource;

public class ExtraStorageLeaderCard extends LeaderCard {
    private Resource costOfLeaderCard;
    private Boolean[] extraStorage = {false, false};
    private Resource storageType;

    public ExtraStorageLeaderCard(int victoryPoints, Resource costOfLeaderCard, Resource storageType) {
        this.victoryPoints = victoryPoints;
        this.costOfLeaderCard = costOfLeaderCard;
        this.storageType = storageType;
    }

    public Resource getCostOfLeaderCard() {
        return costOfLeaderCard;
    }

    @Override
    public Resource getStorageType() {
        return storageType;
    }

    @Override
    public int occupiedResources(){
        int result = 0;

        for(Boolean i : extraStorage){
            if(i == true){
                result++;
            }
        }
        return result;
    }
    @Override
    public void addResource() throws OccupiedSlotExtraStorageLeaderCardException {
        for(int i = 0; i < extraStorage.length; i++){
            if(extraStorage[i] == false){
                extraStorage[i] = true;
                return;
            }
        }
        throw new OccupiedSlotExtraStorageLeaderCardException();
    }

    public void removeResource() throws EmptySlotExtraStorageLeaderCardException {
        for(int i = 0; i < extraStorage.length; i++){
            if(extraStorage[i] == true){
                extraStorage[i] = false;
                return;
            }
        }
        throw new EmptySlotExtraStorageLeaderCardException();
    }

}
