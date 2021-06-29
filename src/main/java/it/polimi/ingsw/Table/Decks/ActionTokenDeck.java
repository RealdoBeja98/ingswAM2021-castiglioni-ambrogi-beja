package it.polimi.ingsw.Table.Decks;
import it.polimi.ingsw.Table.Decks.Token.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This Class represents the decks of action token
 */
public class ActionTokenDeck {

    private final ArrayList<ActionToken> deck;

    /**
     * Constructor method of this class
     */
    public ActionTokenDeck(){
        deck = new ArrayList<>();
        putCards(deck);
        shuffle();
    }

    /**
     * This method fills all the decks with cards
     * @param deck array list of deck
     */
    private void putCards(ArrayList<ActionToken> deck) {
        deck.add(new BlackCross1Token());
        deck.add(new BlackCross2Token());
        deck.add(new BlueActionToken());
        deck.add(new GreenActionToken());
        deck.add(new PurpleActionToken());
        deck.add(new YellowActionToken());
    }

    /**
     * This method draws the card on top
     * @return the card that was on top, of type ActionToken
     */
    public ActionToken draw(){
        ActionToken token = deck.remove(0);
        deck.add(token);
        return token;
    }

    /**
     * This method shuffles the deck
     */
    public void shuffle(){
        Collections.shuffle(deck);
    }

    /**
     * Getter of the parameter deck
     * @return a copy of the deck of action token, of type ArrayList<ActionToken>
     */
    public ArrayList<ActionToken> getDeck() {
        return (ArrayList<ActionToken>)deck.clone();
    }

}
