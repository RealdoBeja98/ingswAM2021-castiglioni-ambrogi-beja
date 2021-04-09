package it.polimi.ingsw.Table.Decks;

public class ResourceProduction {
    private int requiredCoin;
    private int requiredServant;
    private int requiredShield;
    private int requiredStone;
    private int requiredGeneric;
    private int productionCoin;
    private int productionServant;
    private int productionShield;
    private int productionStone;
    private int productionGeneric;
    private int productionFaith;

    public ResourceProduction(int requiredCoin, int requiredServant, int requiredShield,
                              int requiredStone, int requiredGeneric, int productionCoin,
                              int productionServant, int productionShield, int productionStone,
                              int productionGeneric, int productionFaith) {
        this.requiredCoin = requiredCoin;
        this.requiredServant = requiredServant;
        this.requiredShield = requiredShield;
        this.requiredStone = requiredStone;
        this.requiredGeneric = requiredGeneric;
        this.productionCoin = productionCoin;
        this.productionServant = productionServant;
        this.productionShield = productionShield;
        this.productionStone = productionStone;
        this.productionGeneric = productionGeneric;
        this.productionFaith = productionFaith;
    }

    public ResourceProduction sum(ResourceProduction summing){
        return new ResourceProduction(requiredCoin + summing.requiredCoin, requiredServant + summing.requiredServant,
                requiredShield + summing.requiredShield, requiredStone + summing.requiredStone,
                requiredGeneric + summing.requiredGeneric, productionCoin + summing.productionCoin,
                productionServant + productionServant, productionShield + summing.productionShield,
                productionStone + summing.productionStone, productionGeneric + summing.productionGeneric,
                productionFaith + summing.productionFaith);
    }

    public int getRequiredCoin() {
        return requiredCoin;
    }

    public int getRequiredServant() {
        return requiredServant;
    }

    public int getRequiredShield() {
        return requiredShield;
    }

    public int getRequiredStone() {
        return requiredStone;
    }

    public int getRequiredGeneric() {
        return requiredGeneric;
    }

    public int getProductionCoin() {
        return productionCoin;
    }

    public int getProductionServant() {
        return productionServant;
    }

    public int getProductionShield() {
        return productionShield;
    }

    public int getProductionStone() {
        return productionStone;
    }

    public int getProductionGeneric() {
        return productionGeneric;
    }

    public int getProductionFaith() {
        return productionFaith;
    }
}
