package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Market.Marbles.Marble;

/**
 * This Class represents the leader card that give a discount
 */
public class WhiteMarbleLeaderCard extends LeaderCard {

    private final Type[] costOfLeaderCard;
    private final Marble whiteMarble;

    /**
     * Constructor method of this class
     */
    public WhiteMarbleLeaderCard(int victoryPoints, Type[] costOfLeaderCard, Marble whiteMarble){
        this.whatIAm = LeaderCardType.WHITE;
        this.victoryPoints = victoryPoints;
        this.costOfLeaderCard = costOfLeaderCard;
        this.whiteMarble = whiteMarble;
    }

    /**
     * Getter of the parameter costOfLeaderCard
     * @return a copy of the array that represent the cost of the card, of type Type[]
     */
    public Type[] getCostOfLeaderCard(){
        return costOfLeaderCard.clone();
    }

    /**
     * Getter of the parameter whiteMarble
     * @return the marble that replace a white marble, of type Marble
     */
    public Marble getWhiteMarble(){
        return whiteMarble;
    }

    @Override
    public String toString(){
        return "WhiteMarbleLeaderCard";
    }

}
