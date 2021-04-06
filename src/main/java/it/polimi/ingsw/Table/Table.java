package it.polimi.ingsw.Table;
import it.polimi.ingsw.Table.Deck.DevelopmentDeck;
import it.polimi.ingsw.Table.Deck.LeaderDeck;
import it.polimi.ingsw.Table.Market.Market;

/**
 * This Class contains all the elements shared between the players
 */
public class Table {
    private final LeaderDeck leaderDeck;
    private final Market market;
    private final DevelopmentDeck[][] developmentDeck;

    /**
     * Constructor method of this class
     */
    public Table(){
        developmentDeck = new DevelopmentDeck[3][4];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                developmentDeck[i][j] = new DevelopmentDeck();
            }
        }
        market = new Market();
        leaderDeck = new LeaderDeck();
    }

    /**
     * Getter of the parameter leaderDeck
     * @return the deck of leader cards, of type LeaderDeck
     */
    public LeaderDeck getLeaderDeck() {
        return leaderDeck;
    }

    /**
     * Getter of the parameter market
     * @return the market, of type Market
     */
    public Market getMarket() {
        return market;
    }

    /**
     * Getter of the parameter developmentDeck
     * @return the decks of development cards, of type DevelopmentDeck[][]
     */
    public DevelopmentDeck[][] getDevelopmentDeck() {
        return developmentDeck;
    }
}
