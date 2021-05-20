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
        ArrayList<String> playersAndCardsInHandFirst = new ArrayList<>();
        for (Player i : game.getPlayers()){
            playersAndCardsInHandFirst.add(i.exportForPlayersAndCardsInHandFirst());
        }
        all = market + "#" + developmentDeck;
        for(String i : playersAndCardsInHandFirst){
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
        ArrayList<String> playersAndCardsInHandFirst = new ArrayList<>();
        for(int i = 2; i < strings.length; i++){
            playersAndCardsInHandFirst.add(strings[i]);
        }
        PlayerGame playerGame = new PlayerGame(market, developmentDeck, playersAndCardsInHandFirst);
        ClientMain.setPlayerGame(playerGame);
        playerGame.setOut(out);
        System.out.println("GAME START!");

        View w = View.get();
        w.showMarket();
        w.showDevCard();
        w.showPersonalBoard();
        //<--FIXME--> Insert instructions to print the four cards of each player
    }

    @Override
    public String toString(){
        return identifier + " " + all;
    }

}
