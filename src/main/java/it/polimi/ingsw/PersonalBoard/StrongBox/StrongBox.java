package it.polimi.ingsw.PersonalBoard.StrongBox;
import it.polimi.ingsw.Exceptions.NegativeResourceException;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.NotAResourceForStrongBoxException;
import it.polimi.ingsw.Utilities.MyInt;

/**
 * This Class represents the strong box associated to a player
 */
public class StrongBox {

    private int coin;
    private int servant;
    private int shield;
    private int stone;

    /**
     * Constructor method of this class
     */
    public StrongBox(){
        coin = 0;
        servant = 0;
        shield = 0;
        stone = 0;
    }

    /**
     * Getter of the parameter coin
     * @return the number of coin, of type int
     */
    public int getCoin(){
        return coin;
    }

    /**
     * Getter of the parameter stone
     * @return the number of stone, of type int
     */
    public int getStone(){
        return stone;
    }

    /**
     * Getter of the parameter servant
     * @return the number of servant, of type int
     */
    public int getServant(){
        return servant;
    }

    /**
     * Getter of the parameter shield
     * @return the number of shield, of type int
     */
    public int getShield(){
        return shield;
    }

    /**
     * This method receives a resource and a position, and then increase the relative internal counter
     * @param r: the resource type
     * @param n: the resource number
     * @throws NotAResourceForStrongBoxException: if the resource passed is not supposed to be stored in the strong box
     */
    public void add(Resource r, int n) throws NotAResourceForStrongBoxException{
        switch (r) {
            case COIN:
                coin += n;
                break;
            case STONE:
                stone += n;
                break;
            case SERVANT:
                servant += n;
                break;
            case SHIELD:
                shield += n;
                break;
            default: throw new NotAResourceForStrongBoxException();
        }
    }

    private int subtract(int resource, int n) throws NegativeResourceException {
        if (resource - n < 0) {
            throw new NegativeResourceException();
        } else {
            resource -= n;
            return resource;
        }
    }

    /**
     * This method receives a resource and a position, and then decrease the relative internal counter
     * @param r: the resource type
     * @param n: the resource number
     * @throws NegativeResourceException: if the internal counter drop below zero
     * @throws NotAResourceForStrongBoxException: if the resource passed is not supposed to be stored in the strong box
     */
    public void remove(Resource r, int n) throws NegativeResourceException, NotAResourceForStrongBoxException {
        switch (r) {
            case COIN:
                coin = subtract(coin, n);
                break;
            case STONE:
                stone = subtract(stone, n);
                break;
            case SERVANT:
                servant = subtract(servant, n);
                break;
            case SHIELD:
                shield = subtract(shield, n);
                break;
            default: throw new NotAResourceForStrongBoxException();
        }
    }

}
