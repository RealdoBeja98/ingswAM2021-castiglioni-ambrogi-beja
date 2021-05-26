package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;
/**
 * Class of a forward message
 */
public class PlayedLeaderCardForwardMessage extends ForwardMessage {

    private String nickname;
    private int pos;

    /**
     * Constructor of the class
     * @param nickname name of the player
     * @param pos position
     */
    public PlayedLeaderCardForwardMessage(String nickname, int pos){
        identifier = "PLAYED_LEADER_CARD";
        this.nickname = nickname;
        this.pos = pos;
    }

    /**
     * This method plays a leader card
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().playLeaderCard(nickname, pos);
        View w = View.get();
        w.showPBCurrent();
    }


    @Override
    public String toString(){
        return identifier + " " + nickname + " " + pos;
    }

}
