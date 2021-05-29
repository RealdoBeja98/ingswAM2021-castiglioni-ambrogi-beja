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
     * This method receives an ArrayList as parameter and fills it with new leader cards
     * @param deck: an ArrayList where the cards are stored
     */
    private void putCards(ArrayList<LeaderCard> deck){

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/resources/LeaderCardsList.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray json = (JSONArray) obj;
            for(Object i : json) {
                int n = (int)((Long) ((JSONObject) i).get("victoryPoints")).longValue();

                switch ((String)(((JSONObject) i).get("whatIAm"))){
                    case "DISCOUNT":
                        JSONArray temp1 = (JSONArray) ((JSONObject) i).get("costOfLeaderCard");
                        Type[] temp2 = {Type.valueOf((String)temp1.get(0)), Type.valueOf((String)temp1.get(1))};
                        deck.add(new DiscountLeaderCard(n, Resource.valueOf((String)((JSONObject) i).get("discount")), temp2));
                        break;
                    case "WHITE":

                        JSONArray temp3 = (JSONArray) ((JSONObject) i).get("costOfLeaderCard");
                        Type[] temp4 = {Type.valueOf((String)temp3.get(0)), Type.valueOf((String)temp3.get(1))};

                        switch ((String)(((JSONObject) i).get("whiteMarble"))){
                            case "Servant":
                                deck.add(new WhiteMarbleLeaderCard(n, temp4, new Servant()));
                                break;
                            case "Shield":
                                deck.add(new WhiteMarbleLeaderCard(n, temp4, new Shield()));
                                break;
                            case "Stone":
                                deck.add(new WhiteMarbleLeaderCard(n, temp4, new Stone()));
                                break;
                            case "Coin":
                                deck.add(new WhiteMarbleLeaderCard(n, temp4, new Coin()));
                                break;
                            default: break;
                        }
                        break;
                    case "STORAGE":
                        deck.add(new ExtraStorageLeaderCard(n, Resource.valueOf((String)((JSONObject) i).get("costOfLeaderCard")), Resource.valueOf((String)((JSONObject) i).get("storageType"))));
                        break;
                    case "PRODUCTIONPOWER":
                        deck.add(new ProductionPowerLeaderCard(n, Type.valueOf((String)((JSONObject) i).get("costOfLeaderCard")), Resource.valueOf((String)((JSONObject) i).get("requirement"))));
                        break;
                    default: break;
                }
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
