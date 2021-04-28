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

}
