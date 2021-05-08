package it.polimi.ingsw.Game;

import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Exceptions.DrawnFromEmptyDeckException;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.PersonalBoard.SlotsDevelopmentCards.SlotsDevelopmentCards;
import it.polimi.ingsw.PersonalBoard.StrongBox.StrongBox;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.Table.Decks.DevelopmentDeck;
import it.polimi.ingsw.Table.Decks.Leader.LeaderCard;
import it.polimi.ingsw.Table.Market.Market;

import java.util.ArrayList;

public class PlayerGame {

    private class PlayerPlayer {

        private String nickname;
        private LeaderCard[] cardsInHand;
        private LeaderCard[] cardsOnTable;
        private int faithTrackPos;
        private WarehouseDepots warehouseDepots;
        private StrongBox strongBox;
        private SlotsDevelopmentCards slotsDevelopmentCards;

    }

    private PlayerPlayer getPlayerPlayerFromNickname(String nickname){
        for(PlayerPlayer i : players){
            if(i.nickname.equals(nickname)){
                return i;
            }
        }
        return null;
    }

    private ArrayList<PlayerPlayer> players;
    private Market market;
    private DevelopmentDeck developmentDeck;

    public PlayerGame(String market, String developmentDeck, ArrayList<String> playersAndCardsInHand){
        this.market = new Market(market);
        this.developmentDeck = new DevelopmentDeck(developmentDeck);
        this.players = new ArrayList<>();
        for(String player : playersAndCardsInHand){
            String[] strings = player.split("/");
            PlayerPlayer playerPlayer = new PlayerPlayer();
            playerPlayer.nickname = strings[2];
            playerPlayer.cardsInHand[0] = LeaderCard.importLeaderCard(strings[0]);
            playerPlayer.cardsInHand[1] = LeaderCard.importLeaderCard(strings[1]);
            playerPlayer.faithTrackPos = 0;
            playerPlayer.warehouseDepots = new WarehouseDepots();
            playerPlayer.strongBox = new StrongBox();
            playerPlayer.slotsDevelopmentCards = new SlotsDevelopmentCards();
            players.add(playerPlayer);
        }
    }

    public void updateMarket(RowColumn rowColumn, int n){
        if(rowColumn == RowColumn.ROW){
            market.chooseRow(n);
        }
        if(rowColumn == RowColumn.COLUMN){
            market.chooseColumn(n);
        }
    }

    public void updateDevelopmentDeck(int x, int y){
        try {
            developmentDeck.draw(x, y);
        } catch (DrawnFromEmptyDeckException e) {
            e.printStackTrace();
        }
    }

    public void discardLeaderCard(String nickname, int pos){
        getPlayerPlayerFromNickname(nickname).cardsInHand[pos] = null;
    }

    public void playLeaderCard(String nickname, int pos){
        getPlayerPlayerFromNickname(nickname).cardsOnTable[pos] = getPlayerPlayerFromNickname(nickname).cardsInHand[pos];
        getPlayerPlayerFromNickname(nickname).cardsInHand[pos] = null;
    }

    //--FIXME continue--

}
