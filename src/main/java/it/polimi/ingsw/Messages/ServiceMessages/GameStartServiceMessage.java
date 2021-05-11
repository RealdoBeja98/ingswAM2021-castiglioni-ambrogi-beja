package it.polimi.ingsw.Messages.ServiceMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.Game.PlayerGame;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ServiceMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;
import java.util.ArrayList;

public class GameStartServiceMessage extends ServiceMessage {

    private String all;

    public GameStartServiceMessage(Game game){
        String market = game.getTable().getMarket().export();
        String developmentDeck = game.getTable().getDevelopmentDeck().export();
        ArrayList<String> playersAndCardsInHand = new ArrayList<>();
        for (Player i : game.getPlayers()){
            playersAndCardsInHand.add(i.exportForPlayersAndCardsInHand());
        }
        all = market + "#" + developmentDeck;
        for(String i : playersAndCardsInHand){
            all = new StringBuilder().append(all).append("#" + i).toString();
        }
        identifier = "GAME_START";
    }

    public GameStartServiceMessage(String all){
        this.all = all;
        identifier = "GAME_START";
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        String[] strings = all.split("#");
        String market = strings[0];
        String developmentDeck = strings[1];
        ArrayList<String> playersAndCardsInHand = new ArrayList<>();
        for(int i = 2; i < strings.length; i++){
            playersAndCardsInHand.add(strings[i]);
        }
        PlayerGame playerGame = new PlayerGame(market, developmentDeck, playersAndCardsInHand);
        ClientMain.setPlayerGame(playerGame);
        System.out.println("GAME START!");
        View w = new Cli();
        w.showMarket();
        w.showDevCard();
        w.showPersonalBoard();
    }

    @Override
    public String toString(){
        return identifier + " " + all;
    }

}
