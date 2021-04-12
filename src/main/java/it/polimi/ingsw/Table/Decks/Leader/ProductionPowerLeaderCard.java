package it.polimi.ingsw.Table.Decks.Leader;

import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Exceptions.OccupiedSlotExtraStorageLeaderCardException;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Table.Decks.Leader.LeaderCard;
import it.polimi.ingsw.Table.Decks.Production;
import it.polimi.ingsw.Table.Decks.ResourceProduction;

public class ProductionPowerLeaderCard extends LeaderCard implements Production {
    private Type costOfLeaderCard;
    private Resource requirement;

    public ProductionPowerLeaderCard(int victoryPoints,Type costOfLeaderCard, Resource requirement){
        this.whatIAm = LeaderCardType.PRODUCTIONPOWER;
        this.costOfLeaderCard = costOfLeaderCard;
        this.requirement = requirement;
        this.victoryPoints = victoryPoints;
    }

    @Override
    public Resource getStorageType() {
        throw new RuntimeException();
    }

    @Override
    public void addResource() throws OccupiedSlotExtraStorageLeaderCardException {
        throw new RuntimeException();
    }

    @Override
    public int occupiedResources() {
        throw new RuntimeException();
    }

    public ResourceProduction resourceProduction() {
        int costCoin = 0;
        int costServant = 0;
        int costShield = 0;
        int costStone = 0;

        switch (requirement){
            case COIN: costCoin = 1;
                break;

            case SERVANT: costServant = 1;
                break;

            case SHIELD: costShield = 1;
                break;

            case STONE: costStone = 1;
                break;

            default: break;

        }
        return new ResourceProduction(costCoin, costServant, costShield, costStone, 0, 0,
                0, 0, 0, 1, 1);
    }

    public Type getCostOfLeaderCard(){
        return costOfLeaderCard;
    }

    public Resource getRequirement(){
        return requirement;
    }

}
