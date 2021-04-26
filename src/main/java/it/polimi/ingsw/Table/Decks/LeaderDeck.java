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
        Type[] card1 = {Type.YELLOW, Type.GREEN};
        deck.add(new DiscountLeaderCard(2, Resource.SERVANT, card1));
        Type[] card2 = {Type.BLUE, Type.PURPLE};
        deck.add(new DiscountLeaderCard(2, Resource.SHIELD, card2));
        Type[] card3 = {Type.GREEN, Type.BLUE};
        deck.add(new DiscountLeaderCard(2, Resource.STONE, card3));
        Type[] card4 = {Type.YELLOW, Type.PURPLE};
        deck.add(new DiscountLeaderCard(2, Resource.COIN, card4));
        Type[] card5 = {Type.YELLOW, Type.BLUE};
        deck.add(new WhiteMarbleLeaderCard(5, card5, new Servant()));
        Type[] card6 = {Type.GREEN, Type.PURPLE};
        deck.add(new WhiteMarbleLeaderCard(5, card6, new Shield()));
        Type[] card7 = {Type.BLUE, Type.YELLOW};
        deck.add(new WhiteMarbleLeaderCard(5, card7, new Stone()));
        Type[] card8 = {Type.PURPLE, Type.GREEN};
        deck.add(new WhiteMarbleLeaderCard(5, card8, new Coin()));
        deck.add(new ExtraStorageLeaderCard(3, Resource.COIN, Resource.STONE));
        deck.add(new ExtraStorageLeaderCard(3, Resource.STONE, Resource.SERVANT));
        deck.add(new ExtraStorageLeaderCard(3, Resource.SERVANT, Resource.SHIELD));
        deck.add(new ExtraStorageLeaderCard(3, Resource.SHIELD, Resource.COIN));
        deck.add(new ProductionPowerLeaderCard(4, Type.YELLOW, Resource.SHIELD));
        deck.add(new ProductionPowerLeaderCard(4, Type.BLUE, Resource.SERVANT));
        deck.add(new ProductionPowerLeaderCard(4, Type.PURPLE, Resource.STONE));
        deck.add(new ProductionPowerLeaderCard(4, Type.GREEN, Resource.COIN));
    }

    private void putCardsNew(ArrayList<LeaderCard> deck){

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/resources/LeaderCardsList.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray json = (JSONArray) obj;
            for(Object i : json) {
                int n = (int) ((JSONObject) i).get("victoryPoints");

                switch ((String)(((JSONObject) i).get("whatIAm"))){
                    case "DISCOUNT":
                        deck.add(new DiscountLeaderCard(n, (Resource) ((JSONObject) i).get("discount"), (Type[]) ((JSONObject) i).get("costOfLeaderCard")));
                        break;

                    case "WHITE":
                        Type[] param = (Type[]) ((JSONObject) i).get("costOfLeaderCard");
                        switch ((String)(((JSONObject) i).get("whiteMarble"))){
                            case "Servant":
                                deck.add(new WhiteMarbleLeaderCard(n, param, new Servant()));
                                break;
                            case "Shield":
                                deck.add(new WhiteMarbleLeaderCard(n, param, new Shield()));
                                break;
                            case "Stone":
                                deck.add(new WhiteMarbleLeaderCard(n, param, new Stone()));
                                break;
                            case "Coin":
                                deck.add(new WhiteMarbleLeaderCard(n, param, new Coin()));
                                break;
                            default: break;
                        }

                    case "STORAGE":
                        deck.add(new ExtraStorageLeaderCard(n, (Resource) ((JSONObject) i).get("costOfLeaderCard"), (Resource) ((JSONObject) i).get("storageType")));
                        break;

                    case "PRODUCTIONPOWER":
                        deck.add(new ProductionPowerLeaderCard(n, (Type) ((JSONObject) i).get("costOfLeaderCard"), (Resource) ((JSONObject) i).get("requirement")));
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
        LeaderCard[] cards = new LeaderCard[2];
        for(int i = 0; i < 2; i++){
            cards[i] = deck.get(0);
            deck.remove(0);
        }
        return cards;
    }

    /**
     * Getter of the parameter deck
     * @return a copy of the deck of leader cards, of type ArrayList<LeaderCard>
     */
    public ArrayList<LeaderCard> getDeck(){
        return (ArrayList<LeaderCard>)deck.clone();
    }

}
