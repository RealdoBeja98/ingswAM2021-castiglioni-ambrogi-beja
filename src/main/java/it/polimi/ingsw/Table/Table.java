package it.polimi.ingsw.Table;
import it.polimi.ingsw.Table.Decks.ActionTokenDeck;
import it.polimi.ingsw.Table.Decks.DevelopmentDeck;
import it.polimi.ingsw.Table.Decks.LeaderDeck;
import it.polimi.ingsw.Table.Market.Market;

/**
 * This Class contains all the elements shared between the players
 */
public class Table {

    private final LeaderDeck leaderDeck;
    private final Market market;
    private final DevelopmentDeck developmentDeck;
    private final ActionTokenDeck actionTokenDeck;

    /**
     * Constructor method of this class
     */
    public Table(){
        developmentDeck = new DevelopmentDeck();
        market = new Market();
        leaderDeck = new LeaderDeck();
        actionTokenDeck = new ActionTokenDeck();
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
     * @return the decks of development cards, of type DevelopmentDeck
     */
    public DevelopmentDeck getDevelopmentDeck() {
        return developmentDeck;
    }

    /**
     * Getter of the parameter ActionTokenDeck
     * @return the deck of action token, of type ActionTokenDeck
     */
    public ActionTokenDeck getActionTokenDeck() {
        return actionTokenDeck;
    }
}
