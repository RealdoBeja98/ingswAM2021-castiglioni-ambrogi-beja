package it.polimi.ingsw.Table.Deck;

import it.polimi.ingsw.Enums.Resource;

public class LeaderDeck {
    private LeaderCard[] deck = new LeaderCard[16];
    private LeaderCard[] cards = new LeaderCard[2];

    public LeaderDeck(){
        deck[0] = new ExtraStorageLeaderCard(5, Resource.COIN, Resource.SERVANT);
    }

    public LeaderCard[] draw(){
        return cards;
    }
}
