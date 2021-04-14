package it.polimi.ingsw.Table.Decks;
import it.polimi.ingsw.Table.Decks.Token.*;
import java.util.ArrayList;
import java.util.Collections;

public class ActionTokenDeck {

    private final ArrayList<ActionToken> deck;

    public ActionTokenDeck(){
        deck = new ArrayList<>();
        putCards(deck);
        shuffle();
    }

    private void putCards(ArrayList<ActionToken> deck) {
        deck.add(new BlackCross1Token());
        deck.add(new BlackCross2Token());
        deck.add(new BlueActionToken());
        deck.add(new GreenActionToken());
        deck.add(new PurpleActionToken());
        deck.add(new YellowActionToken());
    }

    public ActionToken draw(){
        ActionToken token = deck.remove(0);
        deck.add(token);
        return token;
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

}
