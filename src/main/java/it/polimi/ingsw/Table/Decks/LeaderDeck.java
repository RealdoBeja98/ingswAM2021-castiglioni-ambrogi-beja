package it.polimi.ingsw.Table.Decks;
import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Decks.Leader.*;
import it.polimi.ingsw.Table.Market.Marbles.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public static class ContainerLeaderCard { //<--FIXME-->

        public List<DiscountLeaderCard> discountLeaderCardList = new ArrayList<>();
        public List<WhiteMarbleLeaderCard> whiteMarbleLeaderCardList = new ArrayList<>();
        public List<ExtraStorageLeaderCard> extraStorageLeaderCardList = new ArrayList<>();
        public List<ProductionPowerLeaderCard> productionPowerLeaderCardList = new ArrayList<>();

        @Override
        public String toString() {
            return "ContainerLeaderCard [discountLeaderCardList=" + discountLeaderCardList + ", whiteMarbleLeaderCardList=" + whiteMarbleLeaderCardList + ", extraStorageLeaderCardList=" + extraStorageLeaderCardList + ", productionPowerLeaderCardList=" + productionPowerLeaderCardList + "]";
        }

    }

    private void putCardsNew(ArrayList<LeaderCard> deck){

        // https://codingjam.it/gson-da-java-a-json-e-viceversa-primi-passi/

        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader("src/main/resources/LeaderCardsList.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(reader);

        Gson gson = new Gson();
        ContainerLeaderCard container = gson.fromJson(reader, ContainerLeaderCard.class);
        System.out.println(container);

        //ContainerLeaderCard[] reviews = new Gson().fromJson(reader, ContainerLeaderCard[].class);
        //List<ContainerLeaderCard> asList = Arrays.asList(reviews);
        //System.out.println(asList);

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
