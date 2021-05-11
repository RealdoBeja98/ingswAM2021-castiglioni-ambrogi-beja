package it.polimi.ingsw.Table.Decks.Leader;
import it.polimi.ingsw.Enums.LeaderCardType;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Market.Marbles.*;
import java.util.ArrayList;

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

    /**
     * This method exports the WhiteMarbleLeaderCard to a String
     * @return a string with all the card data, of type String
     */
    @Override
    public String export() {
        String result = "W";
        String fromVictoryPoints = String.valueOf(victoryPoints);
        String fromCostOfLeaderCard = "";
        for(Type i : costOfLeaderCard){
            String toAdd = "";
            switch (i){
                case GREEN: toAdd = "g";
                    break;
                case BLUE: toAdd = "b";
                    break;
                case YELLOW: toAdd = "y";
                    break;
                case PURPLE: toAdd = "p";
                    break;
                default: break;
            }
            fromCostOfLeaderCard = new StringBuilder().append(fromCostOfLeaderCard).append(toAdd).toString();
        }
        String fromWhiteMarble = "";
        switch (whiteMarble.getWhatIAm()){
            case FAITH: fromWhiteMarble = "0";
                break;
            case COIN: fromWhiteMarble = "1";
                break;
            case STONE: fromWhiteMarble = "2";
                break;
            case SERVANT: fromWhiteMarble = "3";
                break;
            case SHIELD: fromWhiteMarble = "4";
                break;
            case WHITE: fromWhiteMarble = "5";
                break;
        }
        result = new StringBuilder().append(result).append(fromVictoryPoints).append(fromCostOfLeaderCard).append(fromWhiteMarble).toString();
        return result;
    }

    /**
     * Constructor method of this class from the exported string
     * @param importedString: the string to import
     */
    public WhiteMarbleLeaderCard(String importedString) {
        this.whatIAm = LeaderCardType.WHITE;
        victoryPoints = Integer.parseInt(importedString.substring(1, 2));
        String toWhiteMarble = importedString.substring(importedString.length() - 1);
        if(toWhiteMarble.equals("0")){
            whiteMarble = new Faith();
        } else if(toWhiteMarble.equals("1")){
            whiteMarble = new Coin();
        } else if(toWhiteMarble.equals("2")){
            whiteMarble = new Stone();
        } else if(toWhiteMarble.equals("3")){
            whiteMarble = new Servant();
        } else if(toWhiteMarble.equals("4")){
            whiteMarble = new Shield();
        } else {
            whiteMarble = new White();
        }
        String fromCostOfLeaderCard = importedString.substring(2, importedString.length() - 1);
        ArrayList<Type> toCostOfLeaderCard = new ArrayList<>();
        for(int i = 0; i < fromCostOfLeaderCard.length(); i++){
            String toAdd = fromCostOfLeaderCard.substring(i, i+1);
            if(toAdd.equals("g")){
                toCostOfLeaderCard.add(Type.GREEN);
            } else if(toAdd.equals("b")){
                toCostOfLeaderCard.add(Type.BLUE);
            } else if(toAdd.equals("y")){
                toCostOfLeaderCard.add(Type.YELLOW);
            } else if(toAdd.equals("p")){
                toCostOfLeaderCard.add(Type.PURPLE);
            }
        }
        costOfLeaderCard = new Type[toCostOfLeaderCard.size()];
        toCostOfLeaderCard.toArray(costOfLeaderCard);
    }

}
