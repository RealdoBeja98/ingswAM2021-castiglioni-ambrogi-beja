package it.polimi.ingsw.Table.Deck;

import it.polimi.ingsw.Table.Deck.LeaderCard;

public class LeaderDeck {
    private LeaderCard[] deck = new LeaderCard[16];
    private LeaderCard[] cards = new LeaderCard[2];

    public LeaderCard[] draw(){
        return cards;
    }
}
