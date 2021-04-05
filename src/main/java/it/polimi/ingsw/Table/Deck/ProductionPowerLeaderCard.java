package it.polimi.ingsw.Table.Deck;

import it.polimi.ingsw.Resource;

public class ProductionPowerLeaderCard extends LeaderCard{
    private Type costOfLeaderCard;
    private Resource requirement;

    public ProductionPowerLeaderCard(int victoryPoints,Type costOfLeaderCard, Resource requirement){
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
}
