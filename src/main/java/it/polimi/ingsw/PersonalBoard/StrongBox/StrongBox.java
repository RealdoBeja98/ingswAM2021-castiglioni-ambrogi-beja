package it.polimi.ingsw.PersonalBoard.StrongBox;
import it.polimi.ingsw.Exceptions.NegativeResourceException;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.NotAResourceForStrongBoxException;

public class StrongBox {

    private int coin;
    private int servant;
    private int shield;
    private int stone;

    public StrongBox(){
        coin = 0;
        servant = 0;
        shield = 0;
        stone = 0;
    }

    public int getCoin(){
        return coin;
    }

    public int getStone(){
        return stone;
    }

    public int getServant(){
        return servant;
    }

    public int getShield(){
        return shield;
    }

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

    public void remove(Resource r, int n) throws NegativeResourceException {
        switch (r) {
            case COIN:
                if (coin - n < 0) {
                    throw new NegativeResourceException();
                } else {
                    coin -= n;
                }
                break;
            case STONE:
                if (stone - n < 0) {
                    throw new NegativeResourceException();
                } else {
                    stone -= n;
                }
                break;
            case SERVANT:
                if (servant - n < 0) {
                    throw new NegativeResourceException();
                } else {
                    servant -= n;
                }
                break;
            case SHIELD:
                if (shield - n < 0) {
                    throw new NegativeResourceException();
                } else {
                    shield -= n;
                }
                break;
        }
    }

}
