package it.polimi.ingsw.Table.Decks;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Decks.Leader.*;
import it.polimi.ingsw.Table.Market.Marbles.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This Class represents the deck of leader cards
 */
public class LeaderDeck {

    private final ArrayList<LeaderCard> deck;

    /**
     * Constructor method of this class
     */
    public LeaderDeck(){
        deck = new ArrayList<>();
        putCards(deck);
        Collections.shuffle(deck);
    }

    /**
     * This method let to obtain the cost of leader card to add
     * @param i: an Object of the JSONArray
     * @return the obtained cost of LeaderCard; of type Type[]
     */
    private Type[] obtainCostOfLeaderCard(Object i){
        JSONArray temp = (JSONArray) ((JSONObject) i).get("costOfLeaderCard");
        Type[] obtainedCostOfLeaderCard = {Type.valueOf((String)temp.get(0)), Type.valueOf((String)temp.get(1))};
        return obtainedCostOfLeaderCard;
    }

    /**
     * this method add a leader card
     * @param i: an Object of the JSONArray
     * @param victoryPoints: the number of victory points
     */
    private void putACard(Object i, int victoryPoints){
        Type[] obtainedCostOfLeaderCard;
        switch ((String)(((JSONObject) i).get("whatIAm"))){
            case "DISCOUNT":
                obtainedCostOfLeaderCard = obtainCostOfLeaderCard(i);
                deck.add(new DiscountLeaderCard(victoryPoints, Resource.valueOf((String)((JSONObject) i).get("discount")), obtainedCostOfLeaderCard));
                break;
            case "WHITE":
                obtainedCostOfLeaderCard = obtainCostOfLeaderCard(i);
                switch ((String)(((JSONObject) i).get("whiteMarble"))){
                    case "Servant":
                        deck.add(new WhiteMarbleLeaderCard(victoryPoints, obtainedCostOfLeaderCard, new Servant()));
                        break;
                    case "Shield":
                        deck.add(new WhiteMarbleLeaderCard(victoryPoints, obtainedCostOfLeaderCard, new Shield()));
                        break;
                    case "Stone":
                        deck.add(new WhiteMarbleLeaderCard(victoryPoints, obtainedCostOfLeaderCard, new Stone()));
                        break;
                    case "Coin":
                        deck.add(new WhiteMarbleLeaderCard(victoryPoints, obtainedCostOfLeaderCard, new Coin()));
                        break;
                    default: break;
                }
                break;
            case "STORAGE":
                deck.add(new ExtraStorageLeaderCard(victoryPoints, Resource.valueOf((String)((JSONObject) i).get("costOfLeaderCard")), Resource.valueOf((String)((JSONObject) i).get("storageType"))));
                break;
            case "PRODUCTIONPOWER":
                deck.add(new ProductionPowerLeaderCard(victoryPoints, Type.valueOf((String)((JSONObject) i).get("costOfLeaderCard")), Resource.valueOf((String)((JSONObject) i).get("requirement"))));
                break;
        }
    }

    /**
     * This method receives an ArrayList as parameter and fills it with new leader cards
     * @param deck: an ArrayList where the cards are stored
     */
    private void putCards(ArrayList<LeaderCard> deck){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("src/main/resources/LeaderCardsList.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray json = (JSONArray) obj;
            for(Object i : json) {
                int victoryPoints = (int)((Long) ((JSONObject) i).get("victoryPoints")).longValue();
                putACard(i, victoryPoints);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method draws the first 2 card in the deck and assigns them to a player
     * @return an array with two cards, of type LeaderCard[]
     */
    public LeaderCard[] draw(){
        LeaderCard[] cards = new LeaderCard[4];
        for(int i = 0; i < 4; i++){
            cards[i] = deck.get(0);
            deck.remove(0);
        }
        return cards;
    }

    /**
     * Getter of the parameter deck
     * @return a copy of the deck of leader cards, of type ArrayList of LeaderCard
     */
    public ArrayList<LeaderCard> getDeck(){
        return (ArrayList<LeaderCard>)deck.clone();
    }

}
