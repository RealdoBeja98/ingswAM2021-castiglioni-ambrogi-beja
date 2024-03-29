package it.polimi.ingsw.Messages.ServiceMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Game.PlayerGame;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ServiceMessage;
import it.polimi.ingsw.View.Gui;
import it.polimi.ingsw.View.View;
import java.io.PrintWriter;

/**
 * Class of the game reconnecting service message
 */
public class GameReconnectingServiceMessage extends ServiceMessage {

    private String all;

    /**
     * Constructor of the class
     * @param game: game instance
     */
    public GameReconnectingServiceMessage(Game game){
        all = game.exportState();
        identifier = "GAME_RECONNECTING";
    }

    /**
     * Constructor of the message
     * @param all: a string that codifies the state of the game
     */
    public GameReconnectingServiceMessage(String all){
        this.all = all;
        identifier = "GAME_RECONNECTING";
    }

    /**
     * This method is for setting reade the start of the game for the messages
     * @param game: game instance
     * @param out: send messages to the socket
     */
    @Override
    public void execute(Game game, PrintWriter out){
        PlayerGame playerGame = new PlayerGame(all);
        ClientMain.setPlayerGame(playerGame);
        playerGame.setOut(out);
        View w = View.get();
        if(w instanceof Gui){
            ((Gui)w).reloadView();
        }else{
            if(!ClientMain.getPlayerGame().gameReallyStarted()){
                w.showStartingLC();
            }else{
                w.showMarket();
                w.showDevCard();
                w.showPersonalBoard();
            }
        }
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + all;
    }

}
