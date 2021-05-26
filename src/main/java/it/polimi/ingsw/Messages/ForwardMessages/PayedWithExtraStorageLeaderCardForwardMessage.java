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
public class PayedWithExtraStorageLeaderCardForwardMessage extends ForwardMessage {

    private String nickname;
    private int leaderCardPosition;

    /**
     * Constructor of the class
     * @param nickname name of the player
     * @param leaderCardPosition position coordinate of leader card
     */
    public PayedWithExtraStorageLeaderCardForwardMessage(String nickname, int leaderCardPosition){
        identifier = "PAYED_WITH_EXTRA_STORAGE_LEADER_CARD";
        this.nickname = nickname;
        this.leaderCardPosition = leaderCardPosition;
    }

    /**
     * This method removes the resource to extrastorage leader card
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().removeResourceExtraStorageLeaderCard(nickname, leaderCardPosition);
        View w = View.get();
        w.showPBCurrent();
    }

    @Override
    public String toString(){
        return identifier + " " + nickname + " " + leaderCardPosition;
    }

}
