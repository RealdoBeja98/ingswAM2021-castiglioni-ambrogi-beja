package it.polimi.ingsw.Table.Decks;

/**
 * This Class represents the production power of a card
 */
public class ResourceProduction {
    private final int requiredCoin;
    private final int requiredServant;
    private final int requiredShield;
    private final int requiredStone;
    private final int requiredGeneric;
    private final int productionCoin;
    private final int productionServant;
    private final int productionShield;
    private final int productionStone;
    private final int productionGeneric;
    private final int productionFaith;

    /**
     * Constructor method of this class
     * @param requiredCoin  required coin
     * @param requiredServant required servant
     * @param requiredShield required shield
     * @param requiredStone required stone
     * @param requiredGeneric required  generic
     * @param productionCoin production  coin
     * @param productionServant production servant
     * @param productionShield production shield
     * @param productionStone production stone
     * @param productionGeneric production generic
     * @param productionFaith production faith
     *
     */
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

    /**
     * This method return a new empty ResourceProduction
     * @return a new empty ResourceProduction; of type ResourceProduction
     */
    public static ResourceProduction newEmptyResourceProduction(){
        return new ResourceProduction(0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0);
    }

    /**
     * This method return a new ResourceProduction representing the default production power
     * @return a new ResourceProduction representing the default production power; of type ResourceProduction
     */
    public static ResourceProduction newDefaultProductionPowerResourceProduction(){
        return new ResourceProduction(0, 0, 0,
                0, 2, 0, 0, 0,
                0, 1, 0);
    }

    /**
     * This method receives another ResourceProduction class and adds it to the current one, then create a new ResourceProduction with the newly generated parameter
     * @param summing: tells the method which ResourceProduction to add to the current class
     * @return a new class that has as attribute the sum of two ResourceProduction classes' attributes, of type ResourceProduction
     */
    public ResourceProduction sum(ResourceProduction summing){
        return new ResourceProduction(requiredCoin + summing.requiredCoin,
                requiredServant + summing.requiredServant,
                requiredShield + summing.requiredShield,
                requiredStone + summing.requiredStone,
                requiredGeneric + summing.requiredGeneric,
                productionCoin + summing.productionCoin,
                productionServant + productionServant,
                productionShield + summing.productionShield,
                productionStone + summing.productionStone,
                productionGeneric + summing.productionGeneric,
                productionFaith + summing.productionFaith);
    }

    /**
     * Getter of the parameter requiredCoin
     * @return the number of coin required to activate the card, of type int
     */
    public int getRequiredCoin() {
        return requiredCoin;
    }

    /**
     * Getter of the parameter requiredServant
     * @return the number of servant required to activate the card, of type int
     */
    public int getRequiredServant() {
        return requiredServant;
    }

    /**
     * Getter of the parameter requiredShield
     * @return the number of shield required to activate the card, of type int
     */
    public int getRequiredShield() {
        return requiredShield;
    }

    /**
     * Getter of the parameter requiredStone
     * @return the number of stone required to activate the card, of type int
     */
    public int getRequiredStone() {
        return requiredStone;
    }

    /**
     * Getter of the parameter requiredGeneric
     * @return the number of generic resource required to activate the card, of type int
     */
    public int getRequiredGeneric() {
        return requiredGeneric;
    }

    /**
     * Getter of the parameter productionCoin
     * @return the number of coin produced by the activation of the card, of type int
     */
    public int getProductionCoin() {
        return productionCoin;
    }

    /**
     * Getter of the parameter productionServant
     * @return the number of servant produced by the activation of the card, of type int
     */
    public int getProductionServant() {
        return productionServant;
    }

    /**
     * Getter of the parameter productionShield
     * @return the number of shield produced by the activation of the card, of type int
     */
    public int getProductionShield() {
        return productionShield;
    }

    /**
     * Getter of the parameter productionStone
     * @return the number of stone produced by the activation of the card, of type int
     */
    public int getProductionStone() {
        return productionStone;
    }

    /**
     * Getter of the parameter productionGeneric
     * @return the number of generic resources produced by the activation of the card, of type int
     */
    public int getProductionGeneric() {
        return productionGeneric;
    }

    /**
     * Getter of the parameter productionFaith
     * @return the number of faith produced by the activation of the card, of type int
     */
    public int getProductionFaith() {
        return productionFaith;
    }

}
