package it.polimi.ingsw.Table;
import it.polimi.ingsw.Table.Deck.LeaderDeck;
import it.polimi.ingsw.Table.Market.Market;

public class Table {
    private LeaderDeck leaderDeck = new LeaderDeck();
    private Market market;

    public LeaderDeck getLeaderDeck() {
        return leaderDeck;
    }

    public Market getMarket() {
        return market;
    }
}
