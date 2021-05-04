package it.polimi.ingsw.Table.Decks.Token;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Decks.Card;

/**
 * This Class is an abstraction of an action token
 */
public abstract class ActionToken extends Card {

    protected Type whatIAm;

    /**
     *abstract method: see implementation in son classes for the details
     */
    public Type getWhatIAm(){ return whatIAm; }

    /**
     * This method overrides the classic toString
     * @return a line with the type of the token, of type String
     */
    @Override
    public String toString() {
        return whatIAm.toString();
    }

    /**
     * This method let obtaining an instance of an ActionToken from a String
     * @param string the String representing the ActionToken
     * @return the ActionToken
     */
    public static ActionToken valueof(String string){
        Type type = Type.valueOf(string);
        if(type == null){
            throw new IllegalArgumentException();
        }
        switch (type){
            case GREEN: return new GreenActionToken();
            case BLUE: return new BlueActionToken();
            case YELLOW: return new YellowActionToken();
            case PURPLE: return new PurpleActionToken();
            case BLACKCROSS1: return new BlackCross1Token();
            case BLACKCROSS2: return new BlackCross2Token();
            default: throw new IllegalArgumentException();
        }
    }

}
