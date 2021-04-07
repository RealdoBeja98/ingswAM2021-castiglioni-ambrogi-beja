package it.polimi.ingsw.Table.Decks;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Decks.Leader.*;
import it.polimi.ingsw.Table.Market.marbles.*;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderDeck {
    private ArrayList<LeaderCard> deck;

    public LeaderDeck(){
        deck = new ArrayList<>();
        putCards(deck);
        Collections.shuffle(deck);
    }

    private void putCards(ArrayList<LeaderCard> temp){
        Type[] card1 = {Type.YELLOW, Type.GREEN};
        temp.add(new DiscountLeaderCard(2, Resource.SERVANT, card1));
        Type[] card2 = {Type.BLUE, Type.PURPLE};
        temp.add(new DiscountLeaderCard(2, Resource.SHIELD, card2));
        Type[] card3 = {Type.GREEN, Type.BLUE};
        temp.add(new DiscountLeaderCard(2, Resource.STONE, card3));
        Type[] card4 = {Type.YELLOW, Type.PURPLE};
        temp.add(new DiscountLeaderCard(2, Resource.COIN, card4));
        Type[] card5 = {Type.YELLOW, Type.BLUE};
        temp.add(new WhiteMarbleLeaderCard(5, card5, new Servant()));
        Type[] card6 = {Type.GREEN, Type.PURPLE};
        temp.add(new WhiteMarbleLeaderCard(5, card6, new Shield()));
        Type[] card7 = {Type.BLUE, Type.YELLOW};
        temp.add(new WhiteMarbleLeaderCard(5, card7, new Stone()));
        Type[] card8 = {Type.PURPLE, Type.GREEN};
        temp.add(new WhiteMarbleLeaderCard(5, card8, new Coin()));
        temp.add(new ExtraStorageLeaderCard(3, Resource.COIN, Resource.STONE));
        temp.add(new ExtraStorageLeaderCard(3, Resource.STONE, Resource.SERVANT));
        temp.add(new ExtraStorageLeaderCard(3, Resource.SERVANT, Resource.SHIELD));
        temp.add(new ExtraStorageLeaderCard(3, Resource.SHIELD, Resource.COIN));
        temp.add(new ProductionPowerLeaderCard(4, Type.YELLOW, Resource.SHIELD));
        temp.add(new ProductionPowerLeaderCard(4, Type.BLUE, Resource.SERVANT));
        temp.add(new ProductionPowerLeaderCard(4, Type.PURPLE, Resource.STONE));
        temp.add(new ProductionPowerLeaderCard(4, Type.GREEN, Resource.COIN));
    }

    public LeaderCard[] draw(){
        LeaderCard[] cards = new LeaderCard[2];
        for(int i = 0; i < 2; i++){
            cards[i] = deck.get(0);
            deck.remove(0);
        }
        return cards;
    }
}
